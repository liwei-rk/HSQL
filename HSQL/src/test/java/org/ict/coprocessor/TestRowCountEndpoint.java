package org.ict.coprocessor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.coprocessor.protocol.RowCountProtocol;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-17
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
public class TestRowCountEndpoint {
    /*
    public static Long expectedCount = 20000000l;
    static Configuration conf = HBaseConfiguration.create();
    static HBaseAdmin admin;
    static HTable table;
    static String tableName = "PTEST4";
    static String tableFamily = "p";
   // @BeforeClass
    public static void createTable() throws IOException {
        admin = new HBaseAdmin(conf);
        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
        HColumnDescriptor columnDescriptor = new HColumnDescriptor(tableFamily);
        tableDescriptor.addFamily(columnDescriptor);
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }
        admin.createTable(tableDescriptor);
        setUp();
    }
    public static void dropTable() throws IOException {
        admin = new HBaseAdmin(conf);
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }
    }
    public static void setUp() throws IOException {
        table = new HTable(conf, tableName);
        int batchSize=50000;
        List<Put> putList=new ArrayList<Put>(batchSize);
        for (int i = 0; i < expectedCount; i++) {
            Put p = new Put(Bytes.toBytes(i));
            p.add(Bytes.toBytes(tableFamily), null, Bytes.toBytes(i));
            putList.add(p);
            if(putList.size()==batchSize){
                table.put(putList);
                putList.clear();
            }
        }
    }
    @Ignore
    @Test
    public void testGetRowCount() throws Throwable {
        table = new HTable(conf, tableName);
        long start=System.currentTimeMillis();

        System.out.println("Begin count");
        Map<byte[], Long> results = table.coprocessorExec(RowCountProtocol.class, null, null, new Batch.Call<RowCountProtocol, Long>() {
            public Long call(RowCountProtocol rowCountProtocol) throws IOException {
                return rowCountProtocol.getRowCount();
            }
        });
        Long count = 0L;
        for (Map.Entry<byte[], Long> entry : results.entrySet()) {
            count += entry.getValue();
        }
        long end=System.currentTimeMillis();
        System.out.println("Count is: "+count+" use : "+(end-start)/1000.0);
        //assertEquals(expectedCount, count);
    }
    */
}
