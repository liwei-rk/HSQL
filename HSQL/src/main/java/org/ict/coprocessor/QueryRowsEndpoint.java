package org.ict.coprocessor;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.CoprocessorService;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.hindex.generated.QueryRows;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.IndexRegionScanner;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.regionserver.RegionScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-8
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public class QueryRowsEndpoint extends QueryRows.QueryRowsService implements CoprocessorService, Coprocessor {
    static Map<Long, InternalScanner> scanners = new HashMap<Long, InternalScanner>();
    static Map<Long, Scan> scanMap = new HashMap<Long, Scan>();
    Log LOG = LogFactory.getLog(QueryRowsEndpoint.class);
    RegionCoprocessorEnvironment environment;
    HRegion region;
    AtomicLong ids = new AtomicLong(0);

    @Override
    public Service getService() {
        return this;
    }

    @Override
    public void getScanner(RpcController controller, ClientProtos.Scan request, RpcCallback<QueryRows.ScannerId> done) {
        Scan scan = null;
        RegionScanner scanner = null;
        long id = -1;
        try {
            scan = ProtobufUtil.toScan(request);
            if (region.getCoprocessorHost() != null) {
                scanner = region.getCoprocessorHost().preScannerOpen(scan);
            }
            if (scanner == null) {
                scanner = region.getScanner(scan);
            }
            if (region.getCoprocessorHost() != null) {
                scanner = region.getCoprocessorHost().postScannerOpen(scan, scanner);
            }
            id = addScanner(scanner);
            if(scanner instanceof IndexRegionScanner){
                LOG.info("use index region scanner:"+scanner.getRegionInfo());
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        QueryRows.ScannerId scannerId = QueryRows.ScannerId.newBuilder().setId(id).build();
        done.run(scannerId);
    }

    private long addScanner(RegionScanner scanner) {
        long id = ids.getAndIncrement();
        scanners.put(id, scanner);
        return id;
    }

    @Override
    public void close(RpcController controller, QueryRows.ScannerId request, RpcCallback<QueryRows.Response> done) {
        long id = request.getId();
        InternalScanner rs = scanners.get(id);
        if (rs != null) {
            try {
                rs.close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        done.run(QueryRows.Response.newBuilder().build());
    }

    @Override
    public void next(RpcController controller, QueryRows.ScannerId request, RpcCallback<QueryRows.Response> done) {
        QueryRows.Response.Builder rb = QueryRows.Response.newBuilder();
        long si = request.getId();
        InternalScanner scanner = scanners.get(si);
        if (scanner != null) {
            List<Cell> cells = new ArrayList<Cell>();
            try {
                rb.setRemaining(scanner.next(cells));
                Result r = Result.create(cells);
                rb.setResult(ProtobufUtil.toResult(r));
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        } else {
            LOG.error("No Such ScannerId: " + si);
        }
        done.run(rb.build());
    }
    /*




    public boolean isAggregate(Scan scan){
        return false;
    }
    public Result aggregateNext(long scannerId) {
        Result r=HSQLConstants.NONEResult;
        Scan s=scanMap.get(scannerId);
        ProjectorList pl=new ProjectorList();
        byte[] plb=s.getAttribute(HSQLPropertyConstants.PROJECTORS);
        try {
            HSQLWritableUtils.readFieldsToWriteable(plb,pl);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        List<Projector> projectors=pl.getProjectors();

        return r;
    }
       */

    @Override
    public void start(CoprocessorEnvironment env) throws IOException {
        this.environment = (RegionCoprocessorEnvironment) env;
        this.region = environment.getRegion();
    }

    @Override
    public void stop(CoprocessorEnvironment env) throws IOException {
    }
}
