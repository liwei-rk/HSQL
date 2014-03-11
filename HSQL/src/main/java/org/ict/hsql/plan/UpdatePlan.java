package org.ict.hsql.plan;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.query.HSQLCondition;
import org.ict.hsql.query.HSQLResultSet;
import org.ict.hsql.schema.ColumnValueRef;
import org.ict.hsql.utils.HSQLPropertyConstants;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-2
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class UpdatePlan extends BasePlan {
    Put updateinfo;
    @Override
    void checkValidProperty(HSQLConnection connection) throws HSQLException {
        //To change body of implemented methods use File | Settings | File Templates.
        //To change body of implemented methods use File | Settings | File Templates.
        tableName=(String)prop.get(HSQLPropertyConstants.TABLENAME);
        if (tableName == null) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.INVALID_SQL);
        }
        HSQLCondition hsqlCondition=((HSQLCondition)prop.get(HSQLPropertyConstants.WHERE));
        update=(List< ColumnValueRef>)prop.get(HSQLPropertyConstants.UPDATE);
        System.out.println(update.toString());
        if(hsqlCondition==null) {}
        else{
            cond=hsqlCondition.generateFilter(connection.getTableSchema(tableName));
        }
        updateinfo=new Put(Bytes.toBytes("UPDATE"));
        for(ColumnValueRef c:update){
            updateinfo.add(Bytes.toBytes(c.getColumnFamily()),Bytes.toBytes(c.getColumn()),c.getValue()) ;
        }
    }
    @Override
    public boolean execute(HSQLConnection connection) throws HSQLException {
        int r=executeUpdate(connection);
        return r>=0;
    }

    @Override
    public ResultSet executeQuery(HSQLConnection connection) throws HSQLException {
        ResultSet r=new HSQLResultSet();
        executeUpdate(connection);
        return r;
    }

    @Override
    public int executeUpdate(HSQLConnection connection) throws HSQLException {
        checkValidProperty(connection);
        HTable table=connection.getHTable(tableName);
        final FilterList filters=new FilterList(cond);

        Long count = 0L;
        Map<byte[], Long> results = null;
        try {  /*
            results = table.coprocessorExec(UpdateRowsProtocol.class, null, null, new Batch.Call<UpdateRowsProtocol, Long>() {
                public Long call(UpdateRowsProtocol updateRowsProtocol) throws IOException {
                    return updateRowsProtocol.update(updateinfo, filters);
                }

            });
            for (Map.Entry<byte[], Long> entry : results.entrySet()) {
                count += entry.getValue();
            }
            */
            return count.intValue();
        } catch (Throwable throwable) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }
    }
}
