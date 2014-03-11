package org.ict.hsql.schema;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-28
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class TestTableSchemaCreate {
    HSQLDelegatingExecutor executor;
    String tableName="testschematable";
    String columnList=" (rowkey INT,define:value1 STRING) ";

    @Before
    public void setUp() throws HSQLException {
        executor = HSQLDelegatingExecutorFactory.create();
        executor.init("helloworld");
        String createStat="CREATE TABLE "+tableName+columnList;
        executor.execute(createStat);
    }

    @Test
    public void testTableSchema() throws HSQLException, IOException {
        List<ColumnRef> columns=new ArrayList<ColumnRef>();
        ColumnRef c1=new ColumnRef("rowkey",ColumnRef.Type.INT.ordinal());
        ColumnRef c2=new ColumnRef("define:value1",ColumnRef.Type.STRING.ordinal());
        columns.add(c1);
        columns.add(c2);
        TableSchema schema=new TableSchema(tableName,columns);
        Configuration conf=HBaseConfiguration.create();
        HTable schemaTable=new HTable(conf,SchemaConstants.SCHEMATABLE);
        Get get=new Get(Bytes.toBytes(tableName));
        Result r=schemaTable.get(get);
        TableSchema result=new TableSchema();
        result.deserialize(r.value());
        assertEquals(schema,result);



    }

    @After
    public void tearDown() throws HSQLException {
        executor.execute("DROP TABLE "+tableName);
        executor.close();
    }
}
