package org.ict.hsql.plan;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.exception.HSQLNotSupported;
import org.ict.hsql.query.HSQLResultSet;
import org.ict.hsql.schema.SchemaConstants;
import org.ict.hsql.utils.HSQLPropertyConstants;

import java.io.IOException;
import java.sql.ResultSet;

import static org.ict.hsql.schema.Utils.getIndexTableName;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午1:24
 * To change this template use File | Settings | File Templates.
 */
public class DropTablePlan extends BasePlan {
    public DropTablePlan() {
        type = STATTYPE.DROP;
    }

    @Override
    public boolean execute(HSQLConnection connection) throws HSQLException {
        checkValidProperty(connection);
        HBaseAdmin admin=connection.getAdmin();
        HTable schemaTable=connection.getHTable(SchemaConstants.SCHEMATABLE);
        try {
            if (!admin.tableExists(tableName)) {
                return false;
            } else {
                deleteSchemaRecord(schemaTable);
                dropTable(admin);
                return true;
            }
        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO, e.getMessage());

        }
    }

    private void deleteSchemaRecord(HTable schemaTable) throws IOException {
        Delete d=new Delete(Bytes.toBytes(tableName));
        schemaTable.delete(d);
    }

    private void dropTable(HBaseAdmin admin) throws IOException {
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
        String indexTableName=getIndexTableName(tableName);
        if(admin.tableExists(indexTableName)){
            admin.disableTable(indexTableName);
            admin.deleteTable(indexTableName);
        }
    }

    @Override
    public ResultSet getLastResult() throws HSQLException {
        throw new HSQLNotSupported();
    }

    @Override
    public ResultSet executeQuery(HSQLConnection connection) throws HSQLException {
        ResultSet r=new HSQLResultSet();
        execute(connection);
        return r;
    }

    @Override
    public int executeUpdate(HSQLConnection connection) throws HSQLException {
        if (execute(connection))
            return 0;
        else
            return -1;
    }

    @Override
    public STATTYPE getType() throws HSQLException {
        return super.getType();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    void checkValidProperty(HSQLConnection connection) throws HSQLException {
        tableName = (String) prop.get(HSQLPropertyConstants.TABLENAME);
        if (tableName == null) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.INVALID_SQL);
        }
        return;
    }
}
