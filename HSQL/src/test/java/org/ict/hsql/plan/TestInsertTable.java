package org.ict.hsql.plan;

import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-28
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public class TestInsertTable {
    HSQLDelegatingExecutor executor;
    String tableName="insertRecordtest";
    String valuelist="(1,2)";
    @Before
    public void setUp() throws HSQLException {
        executor= HSQLDelegatingExecutorFactory.create();
        executor.init("helloworld");
        assertTrue(executor.execute("CREATE TABLE " + tableName + "(key INT,value INT)"));
    }
    @Test
    public void testInsertRecord() throws HSQLException {
        String insertStat="INSERT INTO "+tableName+"  VALUES "+valuelist;
        System.out.println(insertStat);
        executor.execute(insertStat);
    }
    @After
    public void tearDown() throws HSQLException{
        executor.execute("DROP TABLE "+tableName);
        executor.close();
    }
}
