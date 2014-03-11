package org.ict.hsql.query;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-8
 * Time: 下午2:40
 * To change this template use File | Settings | File Templates.
 */
public class ProjectorList implements Writable {
    List<Projector> projectors;
    String tableName="justfortest,see ProjectorList source code";
    public String getColumnTypeName(int i){
        return "default type";
    }
    public int getColumnType(int i){
        return projectors.get(i-1).getColumnType();
    }
    public String getTableName() {
        return tableName;
    }

    public List<Projector> getProjectors() {
        return projectors;
    }

    public ProjectorList(List<Projector> projectors) {
        this.projectors = projectors;
    }

    public ProjectorList() {
    }

    @Override
    public void write(DataOutput out) throws IOException {
     out.writeInt(projectors.size());
     for(Projector p:projectors){
            p.write(out);
        }
    }

    @Override
    public void readFields(DataInput in) throws IOException {

        int s=in.readInt();
        projectors=new ArrayList<Projector>(s);
        for(int i=0;i<s;i++){
             Projector p=new Projector();
             p.readFields(in);
             projectors.add(p);
        }
    }
}
