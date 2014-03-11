package org.ict.coprocessor;

import com.google.protobuf.ByteString;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.coprocessor.hindex.generated.ChangeMetaData;
import org.apache.hadoop.hbase.ipc.BlockingRpcCallback;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.WritableUtils;
import org.ict.hsql.HSQLDelegatingExecutorFactory;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.ict.hsql.schema.SchemaConstants;
import org.ict.hsql.schema.TableSchema;
import org.ict.hsql.utils.HSQLWritableUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-16
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class TestChangeMetaDataEp {
    HSQLDelegatingExecutor executor;
    String columnList=" (rowkey INT,define:value1 STRING) ";
    String tableName = "testChangeMetaDataEndpoint";
    Configuration conf ;//= HBaseConfiguration.create();
    HTable table;
    static TableSchema schema;
    @Before
    public void  setUp() throws IOException, HSQLException {
        conf= HBaseConfiguration.create();
        HBaseAdmin admin=new HBaseAdmin(conf);
        //table=new HTable(conf,tableName);
        executor= HSQLDelegatingExecutorFactory.create();
        executor.init("helloworld");
        if(admin.tableExists(tableName)){
            executor.execute("DROP TABLE "+tableName);
        }
        String createStat="CREATE TABLE "+tableName+columnList;
        assertTrue(executor.execute(createStat));
        Get get=new Get(Bytes.toBytes(tableName));
        HTable schemaTable=new HTable(conf, SchemaConstants.SCHEMATABLE);
        Result r=schemaTable.get(get);
        schema=new TableSchema();
        HSQLWritableUtils.readFieldsToWriteable(r.value(),schema);
        System.out.println(schema.toString());
    }
    @Test
    public void  testChangeMetaDataEndpoint() throws Throwable {
        changeMetaData(tableName);
    }

    private void changeMetaData(String test) throws Throwable {
        final ChangeMetaData.ChangeMetaRequest request= ChangeMetaData.ChangeMetaRequest.newBuilder().setMeta(ByteString.copyFrom(WritableUtils.toByteArray(schema))).build();
        System.out.println(schema.getTableName());
        table=new HTable(conf,tableName);
        Map results=table.coprocessorService(ChangeMetaData.ChangeService.class,null,null,new Batch.Call<ChangeMetaData.ChangeService, Boolean>() {
            @Override
            public Boolean call(ChangeMetaData.ChangeService instance) throws IOException {
                BlockingRpcCallback<ChangeMetaData.ChangeMetaResponse> done=new BlockingRpcCallback<ChangeMetaData.ChangeMetaResponse>();
                instance.changeMeta(null,request,done);
                HSQLWritableUtils.readFieldsToWriteable(done.get().getMeta().toByteArray(),schema);
                System.out.println(schema.toString());

                return done.get().getMeta().equals(request.getMeta());
            }
        });
        assertTrue(results.size()>=1);
        for(Object region:results.keySet()){
            assertTrue((Boolean) results.get(region));
        }
    }

    @Test
    public void testGetMetaData() throws Throwable {

        changeMetaData(tableName);
        final ChangeMetaData.MetaRequest request=ChangeMetaData.MetaRequest.newBuilder().setTableName(tableName).build();
        Map results=table.coprocessorService(ChangeMetaData.ChangeService.class,null,null,new Batch.Call<ChangeMetaData.ChangeService, ChangeMetaData.ChangeMetaResponse>() {
            @Override
            public ChangeMetaData.ChangeMetaResponse call(ChangeMetaData.ChangeService instance) throws IOException {
                BlockingRpcCallback<ChangeMetaData.ChangeMetaResponse> done=new BlockingRpcCallback<ChangeMetaData.ChangeMetaResponse>();
                instance.getMeta(null, request, done);

                return done.get();
            }
        });
        assertTrue(results.size()>=1);
        for(Object region:results.keySet()){
            assertEquals(ByteString.copyFrom(schema.serialize()),((ChangeMetaData.ChangeMetaResponse)results.get(region)).getMeta());
            //System.out.println(((ChangeMetaData.ChangeMetaResponse)results.get(region)).getMeta().toStringUtf8());
        }
    }
}
