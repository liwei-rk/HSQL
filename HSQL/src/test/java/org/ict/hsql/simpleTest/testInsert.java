package org.ict.hsql.simpleTest;

import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.schema.SchemaConstants;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-28
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
public class testInsert {
    @Ignore
    @Test
    public void testPut() throws IOException {
        Configuration conf= HBaseConfiguration.create();
        HTable schema=new HTable(conf, SchemaConstants.SCHEMATABLE);
        List<Put> puts=new ArrayList<Put>();
        Put p=new Put(Bytes.toBytes("testschemaTable"));
        p.add(Bytes.toBytes(SchemaConstants.SCHEMATABLEFAMILY),Bytes.toBytes("user"),Bytes.toBytes("liwei"));
        puts.add(p);
        schema.put(puts);
    }
    @Test
    public void testGet() throws IOException {
        Configuration conf=HBaseConfiguration.create();
        HTable schema=new HTable(conf,SchemaConstants.SCHEMATABLE);
        Get get=new Get(Bytes.toBytes("insertRecordtest"));
        //Result result=schema.get(get);
        //System.out.println(result.list());
        //Filter filter=null;
    }

}
