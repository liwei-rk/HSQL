package org.ict.hsql.query;

import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.ict.hsql.schema.TableSchema;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-5
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public interface HSQLCondition {
    public void add(HSQLCondition conditon);
    //FilterList can treat as a tree
    public Filter generateFilter(TableSchema tableSchema);
    // to element NOT op
    public Filter generateNotFilter();
}
