package org.ict.coprocessor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.regionserver.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.WritableUtils;
import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.ict.hsql.schema.HIndex;
import org.ict.hsql.utils.HSQLWritableUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-2-17
 * Time: 下午9:37
 * To change this template use File | Settings | File Templates.
 */
public class TestHIndexRecord {
    HIndexRecord record;
    static HSQLDelegatingExecutor executor;
    static String tableName="testRawCreateIndex";
    static String columnList=" (rowkey INT,define:value1 STRING) ";
    static Configuration conf;
    static HBaseAdmin admin;
    @BeforeClass
    public static void setUp() throws IOException, HSQLException {
        conf= HBaseConfiguration.create();
        System.out.println();
        admin=new HBaseAdmin(conf);
        executor= HSQLDelegatingExecutorFactory.create();
        executor.init("helloworld");
        if(admin.tableExists(tableName)){
            executor.execute("DROP TABLE "+tableName);
        }
        executor.execute("CREATE TABLE "+tableName+" "+columnList);
        String createIndex="CREATE INDEX "+"value1"+" ON "+tableName;

        assertTrue(executor.execute(createIndex));
    }
    @Test
    public void testHIndexRecord() throws IOException {
        Configuration conf= HBaseConfiguration.create();
        HTable table=new HTable(conf,tableName);
        List<HRegionInfo> regions=
        admin.getTableRegions(Bytes.toBytes(tableName));
        HIndex hindex=new HIndex("value1");
        Put p= new Put(Bytes.toBytes("hello"));
        p.add(Bytes.toBytes("i"),Bytes.toBytes("value1"),Bytes.toBytes("value1"));
        record=new HIndexRecord(regions.get(0).getStartKey(),p.get(Bytes.toBytes("i"),Bytes.toBytes("value1")).get(0),hindex);
        byte[] indexRow= WritableUtils.toByteArray(record);
        HIndexRecord recovered=new HIndexRecord();
        HSQLWritableUtils.readFieldsToWriteable(indexRow,recovered);
    }
    @Test
        public void testIndexPut() throws HSQLException {
         executor.execute("INSERT INTO "+tableName+" VALUES "+"(1,\'abc\')");

    }
}
