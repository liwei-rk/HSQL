package org.ict.hsql.executor;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.parse.HSQLCustomParser;
import org.ict.hsql.schema.SchemaConstants;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午7:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class HSQLBaseExecutor implements HSQLExecutor {
    HSQLConnection connection;
    HBaseAdmin admin;
    HSQLCustomParser parser;


    protected void createSchemaTableIfNeeded() throws HSQLException {
        try {
            if (!admin.tableExists(SchemaConstants.SCHEMATABLE)) {
                HTableDescriptor schemaDesc = new HTableDescriptor(SchemaConstants.SCHEMATABLE);
                schemaDesc.addFamily(new HColumnDescriptor(SchemaConstants.SCHEMATABLEFAMILY));
                admin.createTable(schemaDesc);
            }
        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }
    }
}
