package org.ict.hsql.query;

import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.schema.ColumnRef;
import org.ict.hsql.schema.ColumnValueRef;
import org.ict.hsql.schema.TableSchema;
import org.ict.hsql.schema.ValueRef;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-5
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
public class HSQLAtomCondition extends HSQLBaseCondition{
    ColumnValueRef columnRef;


    @Override
    public void add(HSQLCondition conditon) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Filter generateFilter(TableSchema tableSchema) {
        //FilterList fl=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        Filter filter=new SingleColumnValueFilter(Bytes.toBytes(columnRef.getColumnFamily()),Bytes.toBytes(columnRef.getColumn()),op,columnRef.getValue());
        //fl.addFilter(filter);
        return filter;
    }

    @Override
    public Filter generateNotFilter() {
        FilterList fl=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        Filter filter=new SingleColumnValueFilter(Bytes.toBytes(columnRef.getColumnFamily()),Bytes.toBytes(columnRef.getColumn()),getNotOp(op),columnRef.getValue());
        fl.addFilter(filter);
        return fl;


    }

    CompareFilter.CompareOp op;
    static CompareFilter.CompareOp getNotOp(CompareFilter.CompareOp op){
        switch(op){
            case EQUAL: return CompareFilter.CompareOp.NOT_EQUAL;
            case NOT_EQUAL:return CompareFilter.CompareOp.EQUAL;
            case LESS:return CompareFilter.CompareOp.GREATER_OR_EQUAL;
            case GREATER_OR_EQUAL:return CompareFilter.CompareOp.LESS;
            case GREATER:return CompareFilter.CompareOp.LESS_OR_EQUAL;
            case LESS_OR_EQUAL:return CompareFilter.CompareOp.GREATER;
            default:return CompareFilter.CompareOp.NO_OP;
        }
    }


    public HSQLAtomCondition(ColumnRef columnRef, ValueRef value, CompareFilter.CompareOp op) {
        this.columnRef = new ColumnValueRef(columnRef,value);

        this.op = op;
    }
}
