package org.ict.hsql.query;

import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.ict.hsql.schema.TableSchema;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-5
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class HSQLAndCondition extends HSQLBaseCondition {

    @Override
    public void add(HSQLCondition conditon) {
        conds.add(conditon);
    }

    @Override
    public Filter generateFilter(TableSchema tableSchema) {
        if(conds.size()==1){
            return conds.get(0).generateFilter(tableSchema);
        }
        FilterList fl=new FilterList(FilterList.Operator.MUST_PASS_ALL);
        for(HSQLCondition c:conds){
            fl.addFilter(c.generateFilter(tableSchema));
        }
        return fl;
    }
    public Filter generateNotFilter(){
        FilterList fl=new FilterList(FilterList.Operator.MUST_PASS_ONE);
        for(HSQLCondition c:conds){
            fl.addFilter(c.generateNotFilter());
        }
        return fl;
    }
}
