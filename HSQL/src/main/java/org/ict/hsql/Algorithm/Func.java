package org.ict.hsql.Algorithm;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-1
 * Time: 下午3:28
 * To change this template use File | Settings | File Templates.
 */
public class Func implements Writable {
    public enum TYPE{SUM,COUNT,MAX,MIN,AVERAGE,NONE;
        static TYPE getType(String t){
                 return NONE;
        }
    }
    TYPE type;
    public  boolean isAggregate(){
        return true;
    }

    public Func(TYPE t) {
        this.type = t;
    }

    public Func() {
        this.type =TYPE.NONE;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(type.toString());
    }

    @Override
    public String toString() {
        return "Func{" +
                "type=" + type +
                '}';
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        String t=in.readUTF();
        this.type= TYPE.getType(t);
    }
}
