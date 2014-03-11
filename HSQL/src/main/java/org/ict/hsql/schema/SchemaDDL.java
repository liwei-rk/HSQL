package org.ict.hsql.schema;

import com.google.protobuf.ByteString;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.coprocessor.hindex.generated.ChangeMetaData;
import org.apache.hadoop.hbase.ipc.BlockingRpcCallback;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.WritableUtils;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-16
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class SchemaDDL {
    public static void updateSchemaInfo(HSQLConnection connection,TableSchema schemaInfo) throws HSQLException, InterruptedIOException, RetriesExhaustedWithDetailsException {
        HTable schema=connection.getHTable(SchemaConstants.SCHEMATABLE);
        List<Put> puts = new ArrayList<Put>();
        byte[] columnFamily= Bytes.toBytes(SchemaConstants.SCHEMATABLEFAMILY);
        byte[] column=Bytes.toBytes(SchemaConstants.SCHEMATABLECOLUMN);
        Put p=new Put(Bytes.toBytes(schemaInfo.getTableName()));
        p.add(columnFamily,column,schemaInfo.serialize());
        schema.put(p);
        connection.updateTableSchema(schemaInfo.getTableName(),schemaInfo);
    }
    public static void updateCoprocessorInfo(HSQLConnection connection,TableSchema schemaInfo) throws Throwable {
        HTable schema=connection.getHTable(schemaInfo.getTableName());
        final ChangeMetaData.ChangeMetaRequest request= ChangeMetaData.ChangeMetaRequest.newBuilder().setMeta(ByteString.copyFrom(WritableUtils.toByteArray(schemaInfo))).build();
        System.out.println(schemaInfo.getTableName());
        Map results=schema.coprocessorService(ChangeMetaData.ChangeService.class,null,null,new Batch.Call<ChangeMetaData.ChangeService, Boolean>() {
            @Override
            public Boolean call(ChangeMetaData.ChangeService instance) throws IOException {
                BlockingRpcCallback<ChangeMetaData.ChangeMetaResponse> done=new BlockingRpcCallback<ChangeMetaData.ChangeMetaResponse>();
                instance.changeMeta(null,request,done);

                return done.get().getMeta().equals(request.getMeta());
            }
        });
        assertTrue(results.size()>=1);
        for(Object region:results.keySet()){
            assertTrue((Boolean) results.get(region));
        }

    }
}
