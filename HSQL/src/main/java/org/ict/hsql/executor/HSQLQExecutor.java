package org.ict.hsql.executor;

import org.ict.hsql.connection.HSQLQConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.parse.HSQLCustomParser;
import org.ict.hsql.plan.QueryPlan;

import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public class HSQLQExecutor extends HSQLBaseExecutor {

    @Override
    public void init(String confPath) throws HSQLException {
        connection = new HSQLQConnection(confPath);
        admin = connection.getAdmin();
        createSchemaTableIfNeeded();
    }

    @Override
    public boolean execute(HSQLExecutorContext cxt, String sql) throws HSQLException {
        QueryPlan plan=HSQLCustomParser.compile(sql);
        return plan.execute(connection);
    }

    @Override
    public ResultSet getLastResult() throws HSQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet executeQuery(HSQLExecutorContext cxt, String sql) throws HSQLException {
        QueryPlan plan=HSQLCustomParser.compile(sql);
        return plan.executeQuery(connection);
    }

    @Override
    public int executeUpdate(HSQLExecutorContext cxt, String sql) throws HSQLException {
        QueryPlan plan=HSQLCustomParser.compile(sql);

        return plan.executeUpdate(connection);    }

    @Override
    public void close() throws HSQLException {
        connection.close();
    }
}
