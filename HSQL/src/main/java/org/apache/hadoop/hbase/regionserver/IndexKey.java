package org.apache.hadoop.hbase.regionserver;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-3-3
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */
public class IndexKey  {
    byte[] regionStartkey;
    byte[] indexName;
    byte[] indexValue;
    byte[] indexRowkey;
    byte[] rowkey;
    int rowkeyOff;
    int rowkeyLen;

    public IndexKey(byte[] regionStartkey, byte[] indexName, byte[] indexValue, byte[] indexRowkey)   {
        this.regionStartkey=regionStartkey;
        this.indexName=indexName;
        this.indexValue=indexValue;
        this.indexRowkey=indexRowkey;

        mkRowkey(regionStartkey, indexName, indexValue, indexRowkey);
    }
    private void mkRowkey()  {
        mkRowkey(this.regionStartkey,this.indexName,this.indexValue,this.indexRowkey);
    }

    public byte[] getRowkey() {
        return rowkey;
    }

    private void mkRowkey(byte[] regionStartkey, byte[] indexName, byte[] indexValue, byte[] indexRowkey) {
        this.rowkeyLen= Bytes.SIZEOF_INT*3+regionStartkey.length+indexName.length+indexValue.length+indexRowkey.length;
        this.rowkey=Bytes.createMaxByteArray(rowkeyLen);
        this.rowkeyOff=0;
        int curOff=this.rowkeyOff;
        Bytes.putInt(rowkey,curOff,regionStartkey.length);
        curOff+=Bytes.SIZEOF_INT;
        Bytes.putBytes(rowkey,curOff,regionStartkey,0,regionStartkey.length);
        curOff+=regionStartkey.length;
        Bytes.putInt(rowkey,curOff,indexName.length);
        curOff+=Bytes.SIZEOF_INT;
        Bytes.putBytes(rowkey,curOff,indexName,0,indexName.length);
        curOff+=indexName.length;
        Bytes.putInt(rowkey,curOff,indexValue.length);
        curOff+=Bytes.SIZEOF_INT;
        Bytes.putBytes(rowkey,curOff,indexValue,0,indexValue.length);
        curOff+=indexValue.length;
        Bytes.putBytes(rowkey,curOff,indexRowkey,0,indexRowkey.length);
    }

    public IndexKey(KeyValue k) {
       this.rowkey=k.getRowArray();
       this.rowkeyOff=k.getRowOffset();
       this.rowkeyLen=k.getRowLength();
    }

    IndexKey replaceRegionStartkey(byte[] regionStartkey)  {
       this.regionStartkey=regionStartkey;
       mkRowkey();
       return this;
    }
}
