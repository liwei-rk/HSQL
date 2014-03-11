package org.apache.hadoop.hbase.regionserver;


import org.ict.hsql.schema.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-2-13
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class IndexRegionSplitPolicy extends IncreasingToUpperBoundRegionSplitPolicy {
    @Override
    protected boolean shouldSplit() {
        return !Utils.isIndexTable(region.getTableDesc().getNameAsString()) && super.shouldSplit();
    }
}
