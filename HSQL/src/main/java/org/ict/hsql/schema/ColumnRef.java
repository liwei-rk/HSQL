package org.ict.hsql.schema;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-20
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public class ColumnRef implements  Writable {
    public static String DEFAULT_COLUMN_FAMILY = "__dfc";
    /*
       need to escape the dot in regx,but the '\' its self also need escape
     */

    public ColumnRef(String name) {
        this(name,Type.UNDEFINED.ordinal());
    }

    public static String CF_DELIMITER = ":";
    String columnFamily;
    String column;
    String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnRef columnRef = (ColumnRef) o;

        if (column != null ? !column.equals(columnRef.column) : columnRef.column != null) return false;
        if (columnFamily != null ? !columnFamily.equals(columnRef.columnFamily) : columnRef.columnFamily != null)
            return false;
        if (name != null ? !name.equals(columnRef.name) : columnRef.name != null) return false;
        if (type != columnRef.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = columnFamily != null ? columnFamily.hashCode() : 0;
        result = 31 * result + (column != null ? column.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    Type type;

    public ColumnRef() {
    }


    public ColumnRef(String name, int type) {
       reset(name,type);

    }


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(type.ordinal());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        String name = in.readUTF();
        int type = in.readInt();
        reset(name, type);

    }

    public String getColumnFamily() {
        return columnFamily;
    }

    public String getColumn() {
        return column;
    }

    public Type getType() {
        return type;
    }

    public static byte[] getValue(ValueRef value) {
        Object v=value.getValue();
        if(v instanceof Integer)
            return Bytes.toBytes((Integer)v);
        if(v instanceof Double)
            return Bytes.toBytes((Double)v);
        if(v instanceof Long)
            return Bytes.toBytes((Long)v);
        if(v instanceof String)
            return Bytes.toBytes((String)v);
        return null;
    }

    private void reset(String name, int type) {
        this.type=Type.getType(type);
        String[] strs = name.split(CF_DELIMITER, 2);
        //no match the strs.length will be 1
        //the trailing empty string will removed,so when CF_DELIMITER appear
        //at the tail of name,the length will also be 1
        if (strs.length == 1) {
            this.columnFamily = DEFAULT_COLUMN_FAMILY;
            this.column = name;
        } else {
            this.columnFamily = strs[0];
            this.column = strs[1];
        }
        this.name = this.columnFamily + CF_DELIMITER + this.column;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "ColumnRef{" +
                "columnFamily='" + columnFamily + '\'' +
                ", column='" + column + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public enum Type {
        INT, LONG, DOUBLE, STRING, UNDEFINED;

        static Type getType(int o) {

            if (o == INT.ordinal()) return INT;
            if (o == LONG.ordinal()) return LONG;
            if (o == DOUBLE.ordinal()) return DOUBLE;
            if (o == STRING.ordinal()) return STRING;
            return UNDEFINED;
        }
    }
}
