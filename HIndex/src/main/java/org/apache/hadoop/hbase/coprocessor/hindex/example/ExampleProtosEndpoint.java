/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-14
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
package org.apache.hadoop.hbase.coprocessor.hindex.example;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

public class ExampleProtosEndpoint extends ExampleProtos.RowCountService {
    @Override
    public void getRowCount(RpcController controller, ExampleProtos.CountRequest request, RpcCallback<ExampleProtos.CountResponse> done) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void getKeyValueCount(RpcController controller, ExampleProtos.CountRequest request, RpcCallback<ExampleProtos.CountResponse> done) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
