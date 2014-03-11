package org.ict.hsql.utils;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-8
 * Time: 下午6:28
 * To change this template use File | Settings | File Templates.
 */
public class HSQLConstants {
    public static Result NONEResult;
    static {
       byte[] NONE=Bytes.toBytes("NONE");
       KeyValue kv=new KeyValue(NONE,NONE,NONE,NONE);
       List<KeyValue> kvs=new ArrayList<KeyValue>();
       kvs.add(kv);
       NONEResult=new Result(kvs);

    }

}
