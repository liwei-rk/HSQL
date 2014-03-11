package org.ict.coprocessor.protocol;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.ict.hsql.exception.HSQLException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-2
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public interface QueryRowsProtocol{//} extends CoprocessorProtocol {
    Long getScanner(Scan scan);
    Result next(long scannerId) throws HSQLException;
    Result[] next(long scannerId,int rows);
    void close();
}
