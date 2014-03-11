package org.ict.coprocessor;

import com.google.protobuf.ByteString;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;
import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.coprocessor.CoprocessorService;
import org.apache.hadoop.hbase.coprocessor.hindex.generated.ChangeMetaData;
import org.apache.hadoop.io.WritableUtils;
import org.ict.hsql.schema.SchemaConstants;
import org.ict.hsql.schema.TableSchema;
import org.ict.hsql.utils.HSQLConstants;
import org.ict.hsql.utils.HSQLPropertyConstants;
import org.ict.hsql.utils.HSQLWritableUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-16
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */
public class ChangeMetaDataEndpoint extends ChangeMetaData.ChangeService implements Coprocessor,CoprocessorService{
    public static ConcurrentHashMap<String,TableSchema> schemaCache=new ConcurrentHashMap<String,TableSchema>();


    public ChangeMetaDataEndpoint() {


    }

    @Override
    public void changeMeta(RpcController controller, ChangeMetaData.ChangeMetaRequest request, RpcCallback<ChangeMetaData.ChangeMetaResponse> done) {
        TableSchema schema=new TableSchema();

        try {
            HSQLWritableUtils.readFieldsToWriteable(request.getMeta().toByteArray(),schema);
            schemaCache.put(schema.getTableName(),schema);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ChangeMetaData.ChangeMetaResponse response=ChangeMetaData.ChangeMetaResponse.newBuilder().setMeta(request.getMeta()).build();
        done.run(response);
    }

    @Override
    public void getMeta(RpcController controller, ChangeMetaData.MetaRequest request, RpcCallback<ChangeMetaData.ChangeMetaResponse> done) {
        //To change body of implemented methods use File | Settings | File Templates.
        ChangeMetaData.ChangeMetaResponse response;
        if(schemaCache.size()==0){
            response= ChangeMetaData.ChangeMetaResponse.newBuilder().setMeta(ByteString.copyFromUtf8("bug")).build();
        }
        TableSchema schema=schemaCache.get(request.getTableName());

        response= ChangeMetaData.ChangeMetaResponse.newBuilder().setMeta(ByteString.copyFrom(WritableUtils.toByteArray(schema))).build();
        done.run(response);
    }

    @Override
    public void start(CoprocessorEnvironment env) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
//        env.getInstance().

        //readOrigionSchema(env);
    }

    public static void readOrigionSchema(CoprocessorEnvironment env) {

        try {

            HTableInterface table=env.getTable(TableName.valueOf(SchemaConstants.SCHEMATABLE));
            Scan scan=new Scan();
            ResultScanner results=table.getScanner(scan);
            for(Result result:results){
                TableSchema schema=new TableSchema();
                schema.deserialize(result.value());
                schemaCache.put(schema.getTableName(),schema);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void stop(CoprocessorEnvironment env) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Service getService() {
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
