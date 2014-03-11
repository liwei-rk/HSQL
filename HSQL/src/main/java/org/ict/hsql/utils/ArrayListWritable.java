package org.ict.hsql.utils;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-15
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */
public class ArrayListWritable<E> extends ArrayList<Writable> implements Writable {
    @Override
    public void write(DataOutput out) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        out.writeInt(this.size());
        for(int i=0;i<this.size();i++){
            get(i).write(out);
        }
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        int s=in.readInt();

    }
}
