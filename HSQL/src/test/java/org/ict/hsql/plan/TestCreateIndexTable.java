package org.ict.hsql.plan;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-13
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class TestCreateIndexTable {
    static HSQLDelegatingExecutor executor;
    static String tableName="testCreateIndex";
    static String columnList=" (rowkey INT,define:value1 STRING) ";
    @BeforeClass
    public static void setUp() throws IOException, HSQLException {
        Configuration conf= HBaseConfiguration.create();
        HBaseAdmin admin=new HBaseAdmin(conf);
        executor= HSQLDelegatingExecutorFactory.create();
        executor.init("helloworld");
        if(admin.tableExists(tableName)){
            executor.execute("DROP TABLE "+tableName);
        }
        executor.execute("CREATE TABLE "+tableName+" "+columnList);
    }
    @Test
    public  void testCreateIndex() throws SQLException, IOException {
        String createIndex="CREATE INDEX "+"value1"+" ON "+tableName;
        assertTrue(executor.execute(createIndex));
        String valuelist1="(1,'abc')";
        String valuelist2="(2,'cde')";
        String insertStat1="INSERT INTO "+tableName+"  VALUES "+valuelist1;
        String insertStat2="INSERT INTO "+tableName+"  VALUES "+valuelist2;
        assertTrue(executor.execute(insertStat1));
        assertTrue(executor.execute(insertStat2));
        String select="SELECT rowkey,define:value1 FROM "+tableName+" WHERE define:value1='cde'";
        ResultSet rs=executor.executeQuery(select);
        assertTrue(rs.next());
        assertEquals(2,rs.getInt(1));
        assertEquals("cde",rs.getString(2));
        System.out.println(rs.getString(2));
    }
    @AfterClass
    public static void tearDown() throws HSQLException {
        //executor.execute("DROP TABLE "+tableName);
        executor.close();
    }
}
