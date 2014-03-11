package org.ict.hsql.schema;

import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;
import org.ict.hsql.utils.HSQLWritableUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-28
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public class TableSchema implements Writable {
    private String tableName;

    public TableSchema(String tableName , List<ColumnRef> columns) {
        this.tableName=tableName;
        assert columns!=null&&!columns.isEmpty() :"InVaild SQL";
        List<Pair<ColumnRef,Boolean>> primary=new ArrayList<Pair<ColumnRef, Boolean>>();
        primary.add(new Pair<ColumnRef,Boolean>(columns.get(0),Boolean.TRUE));
        PrimaryKey primaryKey=new PrimaryKey(primary);
        reset(primaryKey,columns);
    }
    public TableSchema() {
        tableName=new String();
        columns=new ArrayList<ColumnRef>();
        primaryKey=new PrimaryKey();
    }

    @Override
    public String toString() {
        return "TableSchema{" +
                "tableName='" + tableName + '\'' +
                ", primaryKey=" + primaryKey +
                ", columns=" + columns +
                ", indexInfos=" + indexInfos +
                '}';
    }

    public MapWritable getIndexInfos() {
        return indexInfos;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(tableName);
        out.writeInt(columns.size());//columns size;
        for(ColumnRef c:columns){
            c.write(out);
        }
        primaryKey.write(out);
        indexInfos.write(out);
    }
    @Override
    public void readFields(DataInput in) throws IOException {
        tableName=in.readUTF();
        int ls=in.readInt();
        columns=new ArrayList<ColumnRef>(ls);
        for(int i=0;i<ls;i++){
            ColumnRef c=new ColumnRef();
            c.readFields(in);
            columns.add(c);
        }
        primaryKey.readFields(in);
        indexInfos.readFields(in);
    }

    public void addIndex(String indexName) {
        //To change body of created methods use File | Settings | File Templates.
        HIndex key=new HIndex(indexName);
        indexInfos.put(key,key);
    }

    public String getTableName() {
        return tableName;
    }

    public static class PrimaryKey implements Writable{
        List<Pair<ColumnRef,Boolean> > primarykey;

        public PrimaryKey() {
        }
        public PrimaryKey(List<Pair<ColumnRef, Boolean>> primarykey) {
            this.primarykey = primarykey;
        }
        public List<Pair<ColumnRef, Boolean>> getPrimarykey() {
            return primarykey;
        }
        @Override
        public void write(DataOutput out) throws IOException {
            out.writeInt(primarykey.size());
            for(Pair<ColumnRef,Boolean> p:primarykey){
                p.getFirst().write(out);
                out.writeBoolean(p.getSecond());
            }
        }
        @Override
        public void readFields(DataInput in) throws IOException {
            int ps=in.readInt();
            primarykey=new ArrayList<Pair<ColumnRef, Boolean>>(ps);
            for(int i=0;i<ps;i++){
                ColumnRef c=new ColumnRef();
                c.readFields(in);
                Boolean b=in.readBoolean();
                primarykey.add(new Pair<ColumnRef, Boolean>(c,b));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PrimaryKey that = (PrimaryKey) o;

            if (!primarykey.equals(that.primarykey)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return primarykey.hashCode();
        }
    }
    PrimaryKey primaryKey;
    List<ColumnRef> columns;
    MapWritable indexInfos=new MapWritable();
    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }
    public List<ColumnRef> getColumns() {
        return columns;
    }
    public ColumnRef getColumn(String unrefinedname){
        ColumnRef u=new ColumnRef(unrefinedname);
        for(ColumnRef c:columns){
            if(c.getName().equals(u.getName()))
                return c;
        }
        return u;
    }
    public TableSchema(String tableName,PrimaryKey primaryKey, List<ColumnRef> columns) {
        this.tableName=tableName;
        reset(primaryKey, columns);
    }

    private void reset(PrimaryKey primaryKey, List<ColumnRef> columns) {
        this.primaryKey = primaryKey;
        this.columns = columns;
    }

    public byte[] serialize(){
        return WritableUtils.toByteArray(this);
    }
    public void deserialize(byte[] inputbuffer) throws IOException {
        HSQLWritableUtils.readFieldsToWriteable(inputbuffer,this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableSchema that = (TableSchema) o;

        if (!columns.equals(that.columns)) return false;
        if (!primaryKey.equals(that.primaryKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryKey.hashCode();
        result = 31 * result + columns.hashCode();
        return result;
    }
}




