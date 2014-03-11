package org.ict.hsql.plan;

import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-4
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class TestUpdateTable {
    /*
    static HSQLDelegatingExecutor executor;
    static String tableName = "testUpdateTable";
    static String columnList = " (rowkey STRING,value1 INT,value2 DOUBLE)";

    @BeforeClass
    public static void setUp() throws HSQLException {
        executor = HSQLDelegatingExecutorFactory.create();
        executor.init("helloworld");
        executor.execute(("DROP TABLE "+tableName));
        assertTrue(executor.execute("CREATE TABLE " + tableName + columnList));
        List<String> values = new ArrayList<String>();
        String column1 = " ('hsql1',1,1.0)";
        values.add(column1);
        String column2 = " ('hsql2',2,2.0)";
        values.add(column2);
        String column3 = " ('hsql3',3,3.0)";
        values.add(column3);
        for (String column : values) {
            assertTrue(executor.execute("INSERT INTO " + tableName + " VALUES "+column));
        }
    }

    @AfterClass
    public static void tearDown() throws HSQLException {
        //executor.execute("DROP TABLE "+tableName);
        executor.close();
    }
    @Test
    public void testUpdateAll() throws Throwable {
        String upstat= "UPDATE  "+tableName+" SET value1=4 ,value2=4.0";
        System.out.println(upstat);
        assertEquals(3, executor.executeUpdate(upstat));
    }
    @Test
    public void testUpdateWhere(){

    }
    */
}
