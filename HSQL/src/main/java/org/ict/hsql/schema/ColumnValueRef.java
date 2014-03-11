package org.ict.hsql.schema;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-28
 * Time: 下午1:03
 * To change this template use File | Settings | File Templates.
 */
public class ColumnValueRef {
    ColumnRef columnSchema;
    ValueRef value;

    public ColumnValueRef(ColumnRef columnSchema, ValueRef value) {
        this.columnSchema = columnSchema;
        this.value = value;
    }
    public String getColumnFamily(){
        return columnSchema.getColumnFamily();
    }
    public String getColumn(){
        return columnSchema.getColumn();
    }
    public byte[] getValue(){
        return ColumnRef.getValue(value);
    }
}
