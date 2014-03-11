package org.ict.hsql.schema;

import org.apache.hadoop.hbase.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-30
 * Time: 下午8:02
 * To change this template use File | Settings | File Templates.
 */
public class TestTableSchema {
        TableSchema schema;
        String tableName="testTableSchema";

    @Before
    public void setUp(){
        List<ColumnRef> columns=new ArrayList<ColumnRef>();
        ColumnRef c1=new ColumnRef("column1",ColumnRef.Type.INT.ordinal());
        ColumnRef c2=new ColumnRef("column2",ColumnRef.Type.STRING.ordinal());
        ColumnRef c3=new ColumnRef("column3",ColumnRef.Type.DOUBLE.ordinal());
        ColumnRef c4=new ColumnRef("column4",ColumnRef.Type.LONG.ordinal());
        columns.add(c1);
        columns.add(c2);
        columns.add(c3);
        columns.add(c4);

        List<Pair<ColumnRef,Boolean>> primary=new ArrayList<Pair<ColumnRef, Boolean>>();
        primary.add(new Pair<ColumnRef,Boolean>(c1,Boolean.TRUE));
        primary.add(new Pair<ColumnRef,Boolean>(c3,Boolean.FALSE));
        TableSchema.PrimaryKey primaryKey=new TableSchema.PrimaryKey(primary);
        schema=new TableSchema(tableName,primaryKey,columns);
    }
    @Test
    public void testSerialize() throws IOException {
        byte[] output=schema.serialize();
        TableSchema cloneSchema=new TableSchema();
        cloneSchema.deserialize(output);
        assertEquals(schema,cloneSchema);
    }

}
