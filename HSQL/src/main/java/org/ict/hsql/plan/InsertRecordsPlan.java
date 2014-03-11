package org.ict.hsql.plan;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.schema.ColumnRef;
import org.ict.hsql.schema.ColumnValueRef;
import org.ict.hsql.schema.TableSchema;
import org.ict.hsql.schema.ValueRef;
import org.ict.hsql.utils.HSQLPropertyConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-26
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
public class InsertRecordsPlan extends BasePlan {
    List<ColumnValueRef> columnValueList = new ArrayList<ColumnValueRef>();

    @Override
    void checkValidProperty(HSQLConnection connection) throws HSQLException {
        List<ValueRef> valueList;

        tableName = (String) prop.get(HSQLPropertyConstants.TABLENAME);
        valueList = (List<ValueRef>) prop.get(HSQLPropertyConstants.VALUELIST);
        TableSchema tableSchema = connection.getTableSchema(tableName);
        List<ColumnRef> columnRefList = tableSchema.getColumns();
        try {
            for (int i = 0; i < valueList.size(); i++) {
                columnValueList.add(new ColumnValueRef(columnRefList.get(i), valueList.get(i)));
            }
        } finally {

        }

    }

    @Override
    public boolean execute(HSQLConnection connection) throws HSQLException {
        return executeUpdate(connection) <= 0 ? false : true;
    }

    @Override
    public int executeUpdate(HSQLConnection connection) throws HSQLException {
        checkValidProperty(connection);
        HTable table = connection.getHTable(tableName);
        List<Put> puts = new ArrayList<Put>();
       // TableSchema.PrimaryKey primaryKey=connection.getTableSchema(tableName).getPrimaryKey();
        for (ColumnValueRef columnValueRef : columnValueList) {
            Put p = new Put(columnValueList.get(0).getValue());
            p.add(Bytes.toBytes(columnValueRef.getColumnFamily()),Bytes.toBytes(columnValueRef.getColumn()),columnValueRef.getValue());
            puts.add(p);
        }
        try {
            table.put(puts);
            return 1;
        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }
    }
}
