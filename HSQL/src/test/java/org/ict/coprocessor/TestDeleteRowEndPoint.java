package org.ict.coprocessor;

import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-2
 * Time: 下午12:36
 * To change this template use File | Settings | File Templates.
 */

public class TestDeleteRowEndPoint {
    /*
    static HSQLDelegatingExecutor executor;
    static String tableName = "testDeleteEndpoint";
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
    //@Ignore
    @Test
    public void testDeletAll() throws Throwable {
        assertEquals(3, executor.executeUpdate("DELETE FROM TABLE "+tableName));
    }
    */
}

