package org.ict.coprocessor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ha.HAAdmin;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.schema.Utils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-2-13
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */
public class TestRegionSplit {
    static Configuration conf;
    static HBaseAdmin admin;
    static String usertable="usertable";
    static String indexTablename=Utils.getIndexTableName(usertable);
    static byte[] splitRow= Bytes.toBytes("midKey");
    @BeforeClass
    public static void setUp() throws IOException {
        conf= HBaseConfiguration.create();
        admin=new HBaseAdmin(conf);
        if(admin.tableExists(usertable)) {
            admin.disableTable(usertable);
            admin.deleteTable(usertable);
        }
        if(admin.tableExists(indexTablename)){
            admin.disableTable(indexTablename);
            admin.deleteTable(indexTablename);
        }
        HTableDescriptor desc=new HTableDescriptor(usertable);
        HColumnDescriptor c=new HColumnDescriptor("c");
        desc.addFamily(c);
        admin.createTable(desc);

        HTableDescriptor idesc=new HTableDescriptor(Utils.getIndexTableName(usertable));
        idesc.addFamily(c);
        admin.createTable(idesc);
    }
    @Test
    public void testUserTableSplit() throws IOException {
        List<HRegionInfo> firstRegion=admin.getTableRegions(TableName.valueOf(usertable));
        try {
            admin.split(firstRegion.get(0).getRegionName(),splitRow);
            Thread.sleep(2000);
            firstRegion=admin.getTableRegions(TableName.valueOf(usertable));
            assertEquals(2,firstRegion.size());
        } catch (InterruptedException e) {
            fail();
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    @Test
    public void testIndexTableSplit() throws IOException, InterruptedException {
        List<HRegionInfo> firstRegion=admin.getTableRegions(TableName.valueOf(indexTablename));
        int size=firstRegion.size();
        admin.split(firstRegion.get(0).getRegionName(),splitRow);
        firstRegion=admin.getTableRegions(TableName.valueOf(indexTablename));
        assertEquals(firstRegion.size(),size);

    }
}
