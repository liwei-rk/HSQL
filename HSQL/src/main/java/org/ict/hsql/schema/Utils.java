package org.ict.hsql.schema;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-1-15
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
     public static String getIndexTableName(String primaryTable){
        return primaryTable+Constants.INDEX_TABLE_SUFFIX;
    }
    public static boolean isIndexTable(String primaryTable){
        return primaryTable.endsWith(Constants.INDEX_TABLE_SUFFIX);
    }
}
