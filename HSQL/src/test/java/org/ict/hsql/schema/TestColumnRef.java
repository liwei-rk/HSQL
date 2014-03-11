package org.ict.hsql.schema;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-21
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class TestColumnRef {
    @Test
    public void testDefaultColumnFamily(){
        ColumnRef defaultColumn=new ColumnRef("liwei",ColumnRef.Type.INT.ordinal());
        assertEquals(ColumnRef.DEFAULT_COLUMN_FAMILY, defaultColumn.getColumnFamily());
        assertEquals("liwei", defaultColumn.getColumn());

    }

    @Test
    public void testDefineColumnFamily(){
        ColumnRef userDefinedColumn=new ColumnRef("define"+ColumnRef.CF_DELIMITER+"liwei",ColumnRef.Type.STRING.ordinal());
        assertEquals("define",userDefinedColumn.getColumnFamily());
        assertEquals("liwei",userDefinedColumn.getColumn());
    }
    @Test
    public void testWrite() throws IOException {
        ColumnRef c=new ColumnRef("ljh",ColumnRef.Type.DOUBLE.ordinal());
        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
        DataOutput out=new DataOutputStream(outputstream);
        c.write(out);
        byte[] buffer=outputstream.toByteArray();
        ByteArrayInputStream  inputStream=new ByteArrayInputStream(buffer);
        DataInput input=new DataInputStream(inputStream);
        assertEquals(c.getName(),input.readUTF());
        assertEquals(ColumnRef.Type.DOUBLE.ordinal(),input.readInt());
    }
    @Test
    public void testReadFields() throws IOException {
        ColumnRef c=new ColumnRef("ljh",ColumnRef.Type.DOUBLE.ordinal());
        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
        DataOutput out=new DataOutputStream(outputstream);
        c.write(out);
        byte[] buffer=outputstream.toByteArray();
        ByteArrayInputStream inputStream=new ByteArrayInputStream(buffer);
        DataInput input=new DataInputStream(inputStream);
        ColumnRef n=new ColumnRef();
        n.readFields(input);
        assertEquals(c.getName(),n.getName());
        assertEquals(c.getType(),n.getType());

    }
}
