package org.ict.hsql.simpleTest;

import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-1
 * Time: 下午4:01
 * To change this template use File | Settings | File Templates.
 */
public class SelectOn93 {
    static String tableName="testSelectTable";
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
    @Test
    public void testRawSelect() throws Exception {
        String  selectStmt="SELECT rowkey,value1,value2 FROM "+tableName;
        ResultSet resultSet=executor.executeQuery(selectStmt);
        assertTrue(resultSet!=null);
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getDouble(3));
        }
    }
    @Test
    public void testWhereSelect() throws Exception {
        String  selectStmt="SELECT rowkey,value1,value2 FROM "+tableName;
        String where=" WHERE rowkey=1";
        ResultSet resultSet=executor.executeQuery(selectStmt+where);
        assertTrue(resultSet!=null);

        while(resultSet.next()){
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getDouble(3));
        }
    }
}
