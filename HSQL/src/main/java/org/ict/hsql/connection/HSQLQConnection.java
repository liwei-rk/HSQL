package org.ict.hsql.connection;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.schema.TableSchema;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午5:44
 * To change this template use File | Settings | File Templates.
 */
public class HSQLQConnection extends HSQLBaseConnection {
    public HSQLQConnection(String url) throws HSQLException {
        super(url);
    }

    public void init(String confPath) throws HSQLException {
        tableCache=new HashMap<String, HTable>();
        schemaCache=new HashMap<String, TableSchema>();
        conf = HBaseConfiguration.create();
        try {
            admin = new HBaseAdmin(conf);
        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }
    }

    @Override
    public void close() throws HSQLException {
        try {
            admin.close();
        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }
    }

    @Override
    public HTable getHTable(String tableName) throws HSQLException {
        synchronized (this) {
            if (tableCache.get(tableName)!=null) {
                return tableCache.get(tableName);
            } else {
                try {
                    HTable r= new HTable(conf, tableName);
                    tableCache.put(tableName,r);
                    return r;
                } catch (IOException e) {
                    throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
                }
            }
           }
        }
    }
