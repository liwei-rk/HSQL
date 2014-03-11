package org.ict.hsql.connection;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.schema.SchemaConstants;
import org.ict.hsql.schema.TableSchema;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */

/*
 *  user template design pattern to init automatically when construct
 */
public abstract class HSQLBaseConnection implements HSQLConnection {
    static Configuration conf;//= HBaseConfiguration.create();
    static HBaseAdmin admin;
    Map<String,HTable> tableCache;
    Map<String,TableSchema> schemaCache;

    protected HSQLBaseConnection(String url) throws HSQLException {
        init(url);
    }

    public abstract void init(String url) throws HSQLException;

    public HBaseAdmin getAdmin() {
        return admin;
    }

    public Configuration getConf() {
        return conf;
    }

    public  TableSchema getTableSchema(String tableName) throws HSQLException {
        TableSchema schema=schemaCache.get(tableName);
        if(schema!=null) return schema;
        HTable schemaTable = null;
        try {
            schemaTable = new HTable(conf, SchemaConstants.SCHEMATABLE);
            Get get = new Get(Bytes.toBytes(tableName));
            get.addColumn(Bytes.toBytes(SchemaConstants.SCHEMATABLEFAMILY),Bytes.toBytes(SchemaConstants.SCHEMATABLECOLUMN));
            Result result=schemaTable.get(get);
            TableSchema ts= new TableSchema();//(columns);
            ts.deserialize(result.value());
            schemaCache.put(tableName,ts);
            return ts;
        } catch (IOException e) {
            throw new HSQLException(HSQLException.HSQLExceptionCode.IO);
        }
    }

    @Override
    public void updateTableSchema(String tableName, TableSchema schema) throws HSQLException {
        schemaCache.put(tableName,schema);
    }
}
