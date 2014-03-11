package org.ict.hsql.plan;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.ict.coprocessor.protocol.DeleteRowsProtocol;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.query.HSQLResultSet;
import org.ict.hsql.utils.HSQLPropertyConstants;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-2
 * Time: 上午11:32
 * To change this template use File | Settings | File Templates.
 */
public class DeletePlan extends BasePlan {
    public DeletePlan() {
        this.type=STATTYPE.DELETE;
    }

    @Override
    void checkValidProperty(HSQLConnection connection) throws HSQLException {
        //To change body of implemented methods use File | Settings | File Templates.
        tableName=(String)prop.get(HSQLPropertyConstants.TABLENAME);
        if (tableName == null) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.INVALID_SQL);
        }
        cond=(Filter)prop.get(HSQLPropertyConstants.WHERE);
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
        try {
            /*
            results = table.coprocessorExec(DeleteRowsProtocol.class, null, null, new Batch.Call<DeleteRowsProtocol, Long>() {
                public Long call(DeleteRowsProtocol deleteRowsProtocol) throws IOException {
                    return deleteRowsProtocol.delete(filters);
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
