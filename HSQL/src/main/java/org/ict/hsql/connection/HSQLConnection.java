package org.ict.hsql.connection;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.schema.TableSchema;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public interface HSQLConnection {
    public void close() throws HSQLException;
    public HBaseAdmin getAdmin();
    public HTable getHTable(String tableName) throws HSQLException;
    public TableSchema getTableSchema(String tableName) throws HSQLException;
    public void updateTableSchema(String tableName,TableSchema schema) throws HSQLException;
}
