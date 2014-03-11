package org.ict.hsql.query;

import org.apache.hadoop.hbase.filter.FilterList;
import org.ict.hsql.schema.TableSchema;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-5
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class HSQLNotCondition extends HSQLBaseCondition {
    HSQLCondition cond;


    public HSQLNotCondition(HSQLCondition cond) {
        this.cond = cond;
    }

    @Override
    public void add(HSQLCondition conditon) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FilterList generateFilter(TableSchema tableSchema) {
          return generateNotFilter();
    }

    @Override
    public FilterList generateNotFilter() {
        FilterList fl=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        fl.addFilter(cond.generateNotFilter());
        return fl;
    }
}
