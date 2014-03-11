package org.ict.hsql.executor;

import org.ict.hsql.exception.HSQLException;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class HSQLDelegatingExecutor implements HSQLDelegatingExecutorInterface{
    static Map<HSQLExecutorContext.ISOLATION_LEVEL,HSQLExecutor> executors;
    HSQLExecutor currentExecutor;
    HSQLExecutorContext context;
    static{
        executors=new HashMap<HSQLExecutorContext.ISOLATION_LEVEL, HSQLExecutor>();
        executors.put(HSQLExecutorContext.ISOLATION_LEVEL.NONE,new HSQLQExecutor());
    }
    public HSQLDelegatingExecutor(HSQLExecutorContext context) {
        this.context=context;
        currentExecutor=executors.get(context.getIsolation_level());
    }
    @Override
    public void init(String confPath) throws HSQLException {
        currentExecutor.init(confPath);
    }
    private void changeCurrentExecutorIfNeeded() {
    }
    @Override
    public boolean execute(String sql) throws HSQLException {
       changeCurrentExecutorIfNeeded();
       return currentExecutor.execute(context,sql);
    }
    @Override
    public ResultSet getLastResult() throws HSQLException {
        changeCurrentExecutorIfNeeded();
        return currentExecutor.getLastResult();
    }

    @Override
    public ResultSet executeQuery(String sql) throws HSQLException {
        changeCurrentExecutorIfNeeded();
        return currentExecutor.executeQuery(context,sql);
    }

    @Override
    public int executeUpdate(String sql) throws HSQLException {
        changeCurrentExecutorIfNeeded();
        return currentExecutor.executeUpdate(context,sql);
    }

    @Override
    public void close() throws HSQLException {
        changeCurrentExecutorIfNeeded();
        currentExecutor.close();
    }
}
