package org.ict.hsql.executor;

import org.ict.hsql.exception.HSQLException;

import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午3:20
 * To change this template use File | Settings | File Templates.
 */
public interface  HSQLExecutor {
    public void init(String confPath) throws HSQLException;
    public boolean execute(HSQLExecutorContext cxt,String sql) throws HSQLException;
    public ResultSet getLastResult() throws HSQLException ;
    public ResultSet executeQuery(HSQLExecutorContext cxt,String sql) throws HSQLException ;
    public int executeUpdate(HSQLExecutorContext cxt,String sql) throws HSQLException;
    public void close() throws HSQLException;
}
