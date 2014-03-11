package org.ict.hsql.plan;

import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-9
 * Time: 下午12:13
 * To change this template use File | Settings | File Templates.
 */
public class TestAggregateTable {
    /*
    static String tableName="testAggregateTable";
    static String columnList=" (rowkey INT,value1 STRING,value2 DOUBLE)";
    static HSQLDelegatingExecutor executor;

    @BeforeClass
    public static void setUp() throws HSQLException {
        executor=new HSQLDelegatingExecutorFactory().create();
        executor.init("Hello World");
        executor.execute("DROP TABLE "+tableName);
        executor.execute("CREATE TABLE "+tableName+columnList);
        String value1="(1,'abc',1.2)";
        String value2="(2,'def',3.1)";
        executor.execute("INSERT INTO "+tableName+" VALUES "+value1);
        executor.execute("INSERT INTO "+tableName+" VALUES "+value2);
    }
    @AfterClass
    public static void tearDown() throws HSQLException {
        //executor.execute("DROP TABLE "+tableName);
        executor.close();
    }
    @Ignore
    @Test
    public void testSum() throws Exception {
        String  selectStmt="SELECT SUM(rowkey) FROM "+tableName;
        ResultSet resultSet=executor.executeQuery(selectStmt);
        assertTrue(resultSet!=null);
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1));
        }
    }
    */
}
