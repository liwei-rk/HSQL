package org.ict.coprocessor.protocol;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.filter.FilterList;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-4
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public interface UpdateRowsProtocol {//extends CoprocessorProtocol {
    //need Filter or FilterList ? not sure what happen if Filter is null
    public long update(Put p,FilterList filters);
}
