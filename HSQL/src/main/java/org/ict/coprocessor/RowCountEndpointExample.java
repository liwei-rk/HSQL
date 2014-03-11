package org.ict.coprocessor;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.coprocessor.protocol.RowCountProtocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-17
 * Time: 下午3:30
 * To change this template use File | Settings | File Templates.
 */
public class RowCountEndpointExample {/*extends BaseEndpointCoprocessor implements RowCountProtocol {

    static int NTHREAD=10;

    class RowCountThread extends Thread{
        byte[] startkey;
        byte[] endkey;
        int result;

        public int getResult() {
            return result;
        }

        RegionCoprocessorEnvironment environment;
        public RowCountThread(byte[] s,byte[] e,RegionCoprocessorEnvironment env){
            this.startkey=s;
            this.endkey=e;
            this.environment=env;
        }
        @Override
        public void run() {
            Scan scan = new Scan();
            scan.setStartRow(startkey);
            scan.setStopRow(endkey);

            InternalScanner internalScanner = null;
            try {
                internalScanner = environment.getRegion().getScanner(scan);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            int result=0;
            try{
                List<KeyValue> curVals=new ArrayList<KeyValue>();
                boolean done=false;
                do{
                    curVals.clear();
                    done=internalScanner.next(curVals);
                    //result+=countKeyValues?curVals.size():1L;
                    result+=1;
                }while(done);
            }catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    internalScanner.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
         this.result=result;
        }
    }

    private long getRowCount(Filter filter, boolean countKeyValues) throws IOException {
        RegionCoprocessorEnvironment environment=(RegionCoprocessorEnvironment) getEnvironment();
        HRegion hRegion=environment.getRegion();
        byte[] startkey=hRegion.getStartKey();
        byte[] endkey=hRegion.getEndKey();
        int s= 0;
        int e=20000000;
        int shard=(e-s)/NTHREAD;       //一个shard 1w
        int s1=s;
        int result=0;
        RowCountThread[] counters=new RowCountThread[NTHREAD];
        for(int i=0;i<NTHREAD;i++){
            counters[i]=new RowCountThread(Bytes.toBytes(s1),Bytes.toBytes(s1+shard),environment);
            counters[i].start();
            s1=s1+shard;
        }
        for(int i=0;i<NTHREAD;i++){
            try {
                counters[i].join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        for(int i=0;i<NTHREAD;i++){
            result+=counters[i].getResult();
        }
        return result;
    }
    @Override
    public long getRowCount() throws IOException {
          return getRowCount(new FirstKeyOnlyFilter(),false);
    }
    */
}
