package org.ict.hsql.query;

import org.apache.hadoop.io.Writable;
import org.ict.hsql.Algorithm.Func;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.Types;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-1
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public class Projector  implements Writable {
    String srcColumn;
    Func fun=new Func();
    int getColumnType(){
         return Types.INTEGER;
    }
    public Projector() {
    }

    public Projector(String srcColumn){
        this.srcColumn=srcColumn;
        this.fun=new Func(Func.TYPE.NONE);
    }
    public Projector(String srcColumn, Func fun) {
        this.srcColumn = srcColumn;
        this.fun = fun;
    }
    public String getSrcColumn() {
        return srcColumn;
    }
    public Func getFun() {
        return fun;
    }
    boolean rawProjector(){
        return fun==null?true:false;
    }
    boolean isAggregate(){
        return fun.isAggregate();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(srcColumn);
        fun.write(out);
    }

    @Override
    public String toString() {
        return "Projector{" +
                "srcColumn='" + srcColumn + '\'' +
                ", fun=" + fun +
                '}';
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        srcColumn=in.readUTF();
        fun.readFields(in);
    }
}
