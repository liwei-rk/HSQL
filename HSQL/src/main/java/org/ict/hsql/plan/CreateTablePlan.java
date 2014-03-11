package org.ict.hsql.plan;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.query.HSQLResultSet;
import org.ict.hsql.schema.ColumnRef;
import org.ict.hsql.schema.SchemaConstants;
import org.ict.hsql.schema.TableSchema;
import org.ict.hsql.utils.HSQLPropertyConstants;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-20
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
public class CreateTablePlan extends BasePlan {


    public  CreateTablePlan() {
        type = STATTYPE.CREATE;
    }

    @Override
    public STATTYPE getType() throws HSQLException {
        return type;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean execute(HSQLConnection connection) throws HSQLException {
        try {
            checkValidProperty(connection);
            HBaseAdmin admin = connection.getAdmin();

            if (admin.tableExists(tableName)) {
                return false;
            } else {
                createSchemaRecord(connection);
                createTable(admin);
                return true;
            }
        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO, e.getMessage());
        }
    }

    private void createTable(HBaseAdmin admin) throws IOException {
        HTableDescriptor desc = new HTableDescriptor(tableName);
        Set<String> families = new HashSet<String>();
        for (ColumnRef col : schemaInfo.getColumns()) {
            families.add(col.getColumnFamily());
        }
        for (String cf : families) {
            desc.addFamily(new HColumnDescriptor(cf));
        }
        admin.createTable(desc);
    }

    private  void createSchemaRecord(HSQLConnection connection) throws IOException, HSQLException {

        HTable schema=connection.getHTable(SchemaConstants.SCHEMATABLE);
        List<Put> puts = new ArrayList<Put>();
        byte[] columnFamily=Bytes.toBytes(SchemaConstants.SCHEMATABLEFAMILY);
        byte[] column=Bytes.toBytes(SchemaConstants.SCHEMATABLECOLUMN);
        Put p=new Put(Bytes.toBytes(tableName));
        p.add(columnFamily,column,schemaInfo.serialize());
        schema.put(p);

    }
    @SuppressWarnings("unchecked")
    void checkValidProperty(HSQLConnection connection) throws HSQLException {
        tableName = ((String) prop.get(HSQLPropertyConstants.TABLENAME));
        System.out.println("TABLE NAME IS"+tableName);
//        tableName="ljh";

        schemaInfo = (TableSchema)prop.get(HSQLPropertyConstants.TABLESCHEMA);
        if (tableName == null || schemaInfo == null || schemaInfo.getColumns().isEmpty()) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.INVALID_SQL, origionSQL);
        }
    }

    @Override
    public ResultSet executeQuery(HSQLConnection connection) throws HSQLException {
        ResultSet r=new HSQLResultSet(schemaInfo);
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

}
