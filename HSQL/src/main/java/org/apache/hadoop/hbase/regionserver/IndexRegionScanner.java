package org.apache.hadoop.hbase.regionserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-2-20
 * Time: 下午7:32
 * To change this template use File | Settings | File Templates.
 */
public class IndexRegionScanner implements RegionScanner {
    Log LOG= LogFactory.getLog(IndexRegionScanner.class);
    Scan scan;
    int batch;
    HRegion region;
    HRegion indexRegion;
    SingleColumnValueFilter sf;
    RegionScanner delegatee;
    public IndexRegionScanner(SingleColumnValueFilter sf, HRegion region,HRegion indexRegion,Scan scan) {
        this.region=region;
        this.indexRegion=indexRegion;
        this.sf=sf;
        this.scan=scan;
        this.batch=scan.getBatch();
        initateDelegateeRegionScanner(sf);
    }
    private void initateDelegateeRegionScanner(SingleColumnValueFilter sf) {
        Scan scan=new Scan();
        byte[] q=sf.getQualifier();
        byte[] v=sf.getComparator().getValue();
        int size=Integer.SIZE+region.getStartKey().length+Integer.SIZE+q.length+Integer.SIZE+v.length;
        ByteArrayBuilder byteArrayBuilder=new ByteArrayBuilder(size);
        byteArrayBuilder.put(Bytes.toBytes(region.getStartKey().length));
        byteArrayBuilder.put(region.getStartKey());
        byteArrayBuilder.put(Bytes.toBytes(q.length));
        byteArrayBuilder.put(q);
        byteArrayBuilder.put(Bytes.toBytes(v.length));
        byteArrayBuilder.put(v);
        byte[] startRow=byteArrayBuilder.array();
        ByteArrayBuilder stopRowArray=new ByteArrayBuilder(startRow.length+1);
        Bytes.zero(stopRowArray.array());
        stopRowArray.put(startRow);
        byte[] stopRow=stopRowArray.array();
        scan.setStartRow(startRow);//.setStopRow(stopRow);
        try {
            delegatee=indexRegion.getScanner(scan);
            //delegatee=indexRegion.getScanner(new Scan());
        } catch (IOException e) {
            LOG.error("here is a bug",e);
        }
    }

    @Override
    public HRegionInfo getRegionInfo() {
        return region.getRegionInfo();
    }

    @Override
    public boolean isFilterDone() throws IOException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean reseek(byte[] row) throws IOException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getMaxResultSize() {
        return Integer.MAX_VALUE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getMvccReadPoint() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean nextRaw(List<Cell> result) throws IOException {
        return next(result,batch);
    }

    @Override
    public boolean nextRaw(List<Cell> result, int limit) throws IOException {
        LOG.info("call next without size");
        List<Cell> indexes=new ArrayList<Cell>();
        boolean r=delegatee.next(indexes);
        for(Cell cell:indexes){
            List<Cell> raw=mkCellFromIndex(cell);
            if(raw!=null&&!raw.isEmpty()) result.addAll(raw);
        }
        LOG.info("hi liwei,the result is ok:"+result.size()+"raw"+indexes.size());
        return r;
    }

    @Override
    public boolean next(List<Cell> results) throws IOException {
         return nextRaw(results);
    }

    private List<Cell> mkCellFromIndex(Cell cell) {
        byte[] regionRow=getRegionRowFromIndex(cell.getRow());
        Get get=new Get(regionRow);
        //get.addColumn(sf.getFamily(),sf.getQualifier());
        try {
            Result r=region.get(get);

            if(r==null){
                LOG.debug("Warning,couldn't find rowkey in index table");
                return null;
            }
            return r.listCells();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private byte[] getRegionRowFromIndex(byte[] rowArray) {
        int off=Integer.SIZE/Byte.SIZE*4+region.getStartKey().length+sf.getQualifier().length+sf.getComparator().getValue().length;
        LOG.debug("index rowkey size is "+rowArray.length+"with "+off);

        int l=rowArray.length-(off);
        return Bytes.copy(rowArray,off,l);
    }

    @Override
    public boolean next(List<Cell> result, int limit) throws IOException {
        return nextRaw(result,limit);
    }

    @Override
    public void close() throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        delegatee.close();
    }
}
