package org.ict.hsql.plan;

import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.WritableUtils;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.query.*;
import org.ict.hsql.utils.HSQLPropertyConstants;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-1
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class SelectPlan extends BasePlan {
    List<Projector> projectors;
    HSQLCondition cond;

    @Override
    void checkValidProperty(HSQLConnection connection) throws HSQLException {
        tableName = (String) prop.get(HSQLPropertyConstants.TABLENAME);
        projectors = (List<Projector>) prop.get(HSQLPropertyConstants.PROJECTORS);
        cond = (HSQLCondition) prop.get(HSQLPropertyConstants.WHERE);
        schemaInfo = connection.getTableSchema(tableName);
    }

    @Override
    public ResultSet executeQuery(HSQLConnection connection) throws HSQLException {
        checkValidProperty(connection);
        Scan scan = new Scan();
        if (cond != null) {
            Filter f = cond.generateFilter(connection.getTableSchema(tableName));
            if (!(f instanceof FilterList)) {
                FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
                fl.addFilter(f);
                scan.setFilter(fl);
            } else {
                scan.setFilter(f);
            }
        }
        scan.setAttribute(HSQLPropertyConstants.TABLENAME, Bytes.toBytes(tableName));
        scan.setAttribute(HSQLPropertyConstants.PROJECTORS, WritableUtils.toByteArray(new ProjectorList(projectors)));
        return new HSQLResultSet(HSQLResultScanner.getScanner(scan, tableName), scan, new ProjectorList(projectors), schemaInfo);
    }
}
