package org.ict.hsql.plan;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午1:52
 * To change this template use File | Settings | File Templates.
 */
public class TestCreateTable {
    HSQLDelegatingExecutor executor;
    String tableName="testCreateTable";
    String columnList=" (rowkey INT,define:value1 STRING) ";
    @Before
    public void setUp() throws IOException, HSQLException {
        Configuration conf= HBaseConfiguration.create();
        HBaseAdmin admin=new HBaseAdmin(conf);
        executor= HSQLDelegatingExecutorFactory.create();
        executor.init("helloworld");
        if(admin.tableExists(tableName)){
            executor.execute("DROP TABLE "+tableName);
        }
    }
    @Test
    public void testCreateTable() throws HSQLException {
        String createStat="CREATE TABLE "+tableName+columnList;
        assertTrue(executor.execute(createStat));
    }
    @After
    public void tearDown() throws HSQLException {
        executor.execute("DROP TABLE "+tableName);
        executor.close();
    }
}
