package org.ict.hsql.query;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.coprocessor.hindex.generated.QueryRows;
import org.apache.hadoop.hbase.ipc.BlockingRpcCallback;
import org.apache.hadoop.hbase.ipc.CoprocessorRpcChannel;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.exception.HSQLNotSupported;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-8
 * Time: 下午2:05
 * To change this template use File | Settings | File Templates.
 */
public class HSQLResultScanner implements ResultScanner {
    static Configuration conf = HBaseConfiguration.create();
    String tableName;
    Scan scan;
    HTable table;
    Map<byte[], QueryRows.ScannerId> scannerIds;
    Map<String, HRegionInfo> hregionMap;

    public HSQLResultScanner(String tableName, HTable table, Map<byte[], QueryRows.ScannerId> scannerIds, Map<String, HRegionInfo> hregionMap) {
        this.tableName = tableName;
        this.table = table;
        this.scannerIds = scannerIds;
        this.hregionMap = hregionMap;
    }

    public HSQLResultScanner() {
    }

    public synchronized static ResultScanner getScanner(final Scan scan, String tableName) throws HSQLException {
        Map<byte[], QueryRows.ScannerId> results = null;
        try {
            HTable table = new HTable(conf, tableName);
            results = table.coprocessorService(QueryRows.QueryRowsService.class, null, null, new Batch.Call<QueryRows.QueryRowsService, QueryRows.ScannerId>() {
                public QueryRows.ScannerId call(QueryRows.QueryRowsService qs) throws IOException {
                    BlockingRpcCallback<QueryRows.ScannerId> done = new BlockingRpcCallback<QueryRows.ScannerId>();
                    qs.getScanner(null, ProtobufUtil.toScan(scan), done);
                    return done.get();
                }
            });
            Map<String, HRegionInfo> hregionMap = new HashMap<String, HRegionInfo>();
            NavigableMap<HRegionInfo, ServerName> hRegionInfos = table.getRegionLocations();
            for (HRegionInfo h : hRegionInfos.keySet()) {
                hregionMap.put(Bytes.toString(h.getRegionName()), h);
            }
            return new HSQLResultScanner(tableName, table, results, hregionMap);
        } catch (Throwable throwable) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }
    }

    @Override
    public Result next() {
        for (final byte[] regionName : scannerIds.keySet()) {
            HRegionInfo hRegion = hregionMap.get(Bytes.toString(regionName));
            try {
                CoprocessorRpcChannel channel = table.coprocessorService(hRegion.getStartKey());
                QueryRows.QueryRowsService.BlockingInterface service = QueryRows.QueryRowsService.newBlockingStub(channel);
                QueryRows.Response r = service.next(null, scannerIds.get(regionName));
                if(r.hasResult()){
                    if(!r.getRemaining()){
                        scannerIds.remove(regionName);
                    }
                    return ProtobufUtil.toResult(r.getResult());
                }else{
                    scannerIds.remove(regionName);
                }
            }catch (Throwable throwable) {
                throwable.printStackTrace();
                return null;
            }
        }
        return null;
    }
    @Override
    public Result[] next(int nbRows) throws IOException {
        try {
            throw new HSQLNotSupported();
        } catch (HSQLNotSupported throwables) {
            throw new IOException(throwables);
        }
    }
    @Override
    public void close() {
        for(byte[] regionName:scannerIds.keySet()){
            close(regionName);
        }
    }

    private void close(byte[] regionName) {
        CoprocessorRpcChannel channel=table.coprocessorService(hregionMap.get(Bytes.toString(regionName)).getStartKey());
        QueryRows.QueryRowsService.BlockingInterface service=QueryRows.QueryRowsService.newBlockingStub(channel);
        if(scannerIds.get(regionName)!=null){
            try {
                service.close(null,scannerIds.get(regionName));
            } catch (ServiceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    @Override
    public Iterator<Result> iterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
