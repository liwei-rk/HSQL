package org.ict.hsql.plan;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
public class TestDropTable {
    static String tableName="testDropTable";
    static String dropTableStatement="DROP TABLE "+tableName;
    static String createTableStatement="CREATE TABLE "+tableName+ "(rowkey INT)";
    static HSQLDelegatingExecutor executor;
    static HBaseAdmin admin;
    static Configuration conf;
    @BeforeClass
    public static void setUp() throws HSQLException, IOException {
            conf= HBaseConfiguration.create();
            admin=new HBaseAdmin(conf);
            executor=HSQLDelegatingExecutorFactory.create();
            executor.init("abc");
    }
    @AfterClass
    public static void tearDown() throws HSQLException{
           executor.close();
    }
    @Before
    public void createTable() throws HSQLException {
           System.out.println(createTableStatement);
           executor.execute(createTableStatement);
    }
    @Test
    public void testDropTable() throws IOException, HSQLException {

           assertTrue(admin.tableExists(tableName));
           executor.execute(dropTableStatement);
           assertTrue(!admin.tableExists(tableName));
    }

}
