package org.ict.hsql.utils;


import org.apache.hadoop.io.Writable;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-30
 * Time: 下午9:01
 * To change this template use File | Settings | File Templates.
 */
public class HSQLWritableUtils {
    public static void readFieldsToWriteable(byte[] input,Writable target) throws IOException {
        ByteArrayInputStream in=new ByteArrayInputStream(input);

        DataInputStream inputStream=new DataInputStream(in);
        target.readFields(inputStream);
    }
}
