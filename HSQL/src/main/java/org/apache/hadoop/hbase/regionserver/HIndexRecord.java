package org.apache.hadoop.hbase.regionserver;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Writable;
import org.ict.hsql.schema.HIndex;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-2-17    aaa bbb aaa
 * Time: 下午5:03
 * To change this template use File | Settings | File Templates.
 */
public class HIndexRecord implements Writable {
    Log LOG= LogFactory.getLog(HIndexRecord.class);
    byte[] regionStartKey;
    byte[] indexName;
    byte[] value;
    byte[] rowkey;
    int size;

    public HIndexRecord setRegionStartKey(byte[] regionStartKey) {
        this.size+=regionStartKey.length-this.regionStartKey.length;
        this.regionStartKey=regionStartKey;
        return this;
    }

    public HIndexRecord() {

    }

    public HIndexRecord(byte[] indexRegionStartKey, Cell m, HIndex index) {
        regionStartKey=indexRegionStartKey;
        indexName=index.getIndexName().getBytes();

        value=m.getValue();

        rowkey=m.getRow();
        size=Integer.SIZE/8*4+regionStartKey.length+indexName.length+value.length+rowkey.length;


    }
    public HIndexRecord deserialize(byte[] in)  {
          ByteArrayDataInput di=ByteStreams.newDataInput(in);
          readFields(di);
          return this;
    }

    public byte[] serialize(){

        ByteArrayDataOutput byteBuffer= ByteStreams.newDataOutput(size);
        byteBuffer.writeInt(regionStartKey.length);
        byteBuffer.write(regionStartKey);
        byteBuffer.writeInt(indexName.length);
        byteBuffer.write(indexName);
        byteBuffer.writeInt(value.length);
        byteBuffer.write(value);
        byteBuffer.writeInt(rowkey.length);
        byteBuffer.write(rowkey);
        return byteBuffer.toByteArray();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(regionStartKey.length);
        out.write(regionStartKey);
        out.writeInt(indexName.length);
        out.write(indexName);
        out.writeInt(value.length);
        out.write(value);
        out.writeInt(rowkey.length);
        out.write(rowkey);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        readFields(in);
    }

    public void readFields(ByteArrayDataInput in)  {

        int s=in.readInt();
        regionStartKey=new byte[s] ;
        in.readFully(regionStartKey,0,s);
        System.out.println(String.valueOf(regionStartKey));
        s=in.readInt();
        indexName=new byte[s];
        in.readFully(indexName,0,s);
        s=in.readInt();
        value=new byte[s];
        in.readFully(value,0,s);
        s=in.readInt();
        rowkey=new byte[s];
        in.readFully(rowkey,0,s);
        this.size=Bytes.SIZEOF_INT*4+regionStartKey.length+indexName.length+value.length+rowkey.length;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HIndexRecord that = (HIndexRecord) o;

        if (!Arrays.equals(indexName, that.indexName)) return false;
        if (!Arrays.equals(regionStartKey, that.regionStartKey)) return false;
        if (!Arrays.equals(rowkey, that.rowkey)) return false;
        if (!Arrays.equals(value, that.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regionStartKey != null ? Arrays.hashCode(regionStartKey) : 0;
        result = 31 * result + (indexName != null ? Arrays.hashCode(indexName) : 0);
        result = 31 * result + (value != null ? Arrays.hashCode(value) : 0);
        result = 31 * result + (rowkey != null ? Arrays.hashCode(rowkey) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HIndexRecord{" +
                "regionStartKey=" + regionStartKey +
                ", indexName=" + indexName +
                ", value=" + value +
                ", rowkey=" + rowkey +
                '}';
    }

    public byte[] getRegionStartKey() {
        return regionStartKey;
    }

    public byte[] getIndexName() {
        return indexName;
    }

    public byte[] getValue() {
        return value;
    }

    public byte[] getRowkey() {
        return rowkey;
    }
}
