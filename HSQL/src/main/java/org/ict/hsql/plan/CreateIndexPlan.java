package org.ict.hsql.plan;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.utils.HSQLPropertyConstants;
import org.ict.hsql.schema.*;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-14
 * Time: 下午10:40
 * To change this template use File | Settings | File Templates.
 */
public class CreateIndexPlan extends BasePlan {
    String indexName;

    @Override
    void checkValidProperty(HSQLConnection connection) throws HSQLException {
        //To change body of implemented methods use File | Settings | File Templates.
        tableName = (String) prop.get(HSQLPropertyConstants.TABLENAME);
        schemaInfo = connection.getTableSchema(tableName);
        indexName = (String) prop.get(HSQLPropertyConstants.INDEXNAME);
    }

    @Override
    public boolean execute(HSQLConnection connection) throws HSQLException {
        return executeUpdate(connection) >= 0;
    }

    @Override
    public int executeUpdate(HSQLConnection connection) throws HSQLException {
        checkValidProperty(connection);
        schemaInfo.addIndex(indexName);
        createIndexTableIfNeed(connection);
        try {
            updateSchemaInfo(connection);
        } catch (InterruptedIOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        } catch (RetriesExhaustedWithDetailsException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        } catch (Throwable throwable) {
            throwable.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    private void updateSchemaInfo(HSQLConnection connection) throws Throwable {
        //To change body of created methods use File | Settings | File Templates.
        SchemaDDL.updateSchemaInfo(connection,schemaInfo);
        SchemaDDL.updateCoprocessorInfo(connection,schemaInfo);

    }

    private void createIndexTableIfNeed(HSQLConnection connection) throws HSQLException {
        //To change body of created methods use File | Settings | File Templates.
        try {
            if (connection.getAdmin().tableExists(getIndexTableName())) {
                return;
            } else {
                createIndexTable(connection);

            }

        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }

    }

    private void createIndexTable(HSQLConnection connection) throws IOException {
        HTableDescriptor indexTable = new HTableDescriptor(TableName.valueOf(getIndexTableName()));
        HColumnDescriptor cf = new HColumnDescriptor("i");
        indexTable.addFamily(cf);
        connection.getAdmin().createTable(indexTable);
    }

    private String getIndexTableName() {
        return tableName + "__idx";
    }
}
