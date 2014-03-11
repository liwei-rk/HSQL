package org.ict.coprocessor.protocol;

import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-2
 * Time: 下午12:25
 * To change this template use File | Settings | File Templates.
 */
public interface DeleteRowsProtocol {//extends CoprocessorProtocol {
    long delete(FilterList filters) throws IOException;
}
