package org.ict.hsql.schema;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-15
 * Time: 下午1:42
 * To change this template use File | Settings | File Templates.
 */
public class HIndex implements Writable {
    public HIndex() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getIndexName() {
        return indexName;
    }

    String indexName;

    public HIndex(String indexName) {
        this.indexName=indexName;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        out.writeUTF(indexName);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        indexName=in.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HIndex hIndex = (HIndex) o;

        if (indexName != null ? !indexName.equals(hIndex.indexName) : hIndex.indexName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return indexName != null ? indexName.hashCode() : 0;
    }
}
