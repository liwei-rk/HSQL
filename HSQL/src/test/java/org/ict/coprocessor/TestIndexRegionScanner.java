package org.ict.coprocessor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.regionserver.HIndexRecord;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.schema.HIndex;
import org.ict.hsql.utils.HSQLWritableUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import sun.reflect.generics.tree.ByteSignature;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-2-21
 * Time: 下午8:51
 * To change this template use File | Settings | File Templates.
 */
public class TestIndexRegionScanner {
    static Configuration conf= HBaseConfiguration.create();
    static HTable table;
    @BeforeClass
    public static void setUp() throws IOException {
        table=new HTable(conf,"testRawCreateIndex");
    }
    @Test
    public  void testPut() throws IOException {
        HTable itable=new HTable(conf,"testRawCreateIndex__idx");
        ResultScanner rs=itable.getScanner(new Scan());
        for(Result r:rs){
            byte[] row=r.getRow();
            HIndexRecord index=new HIndexRecord();
            HSQLWritableUtils.readFieldsToWriteable(row,index);
            System.out.println("liwei why"+index.getRegionStartKey());
            assertArrayEquals(itable.getRegionLocations().firstEntry().getKey().getStartKey(), index.getRegionStartKey());
            assertArrayEquals(index.getIndexName(), Bytes.toBytes("value1"));
            System.out.println("liwei why"+index.getRegionStartKey());

            //assertArrayEquals(index.getValue(),Bytes.toBytes("abc"));
            System.out.println("liwei why"+index.getRegionStartKey());

            assertArrayEquals(index.getRowkey(),Bytes.toBytes(1));


        }
    }
    @Test
    public  void testNoFilter() throws IOException {
        Scan scan=new Scan();
        ResultScanner result=table.getScanner(Bytes.toBytes("define"),Bytes.toBytes("value1"));
        Result r=result.next();
        assertTrue(r!=null);
        byte[] v=r.getValue(Bytes.toBytes("define"), Bytes.toBytes("value1"));
        assertEquals("abc", Bytes.toString(v));
        System.out.println(Bytes.toString(v));
    }
    @Test
    public void testInternalScaner(){
        Scan scan=new Scan();
        SingleColumnValueFilter svf=new SingleColumnValueFilter(Bytes.toBytes("define"),Bytes.toBytes("value1"), CompareFilter.CompareOp.EQUAL,Bytes.toBytes("abc"));
        FilterList filterList=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(svf);
        scan.setFilter(filterList);

    }
    @Test
    public void testFilter() throws IOException {
        Scan scan=new Scan();
        SingleColumnValueFilter svf=new SingleColumnValueFilter(Bytes.toBytes("define"),Bytes.toBytes("value1"), CompareFilter.CompareOp.EQUAL,Bytes.toBytes("abc"));
        FilterList list=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        list.addFilter(svf);
        scan.setFilter(list);
        ResultScanner results=table.getScanner(scan);
        Result r=results.next();
        assertTrue(r!=null);
        System.out.println(Bytes.toInt(r.getRow()));
    }

}
