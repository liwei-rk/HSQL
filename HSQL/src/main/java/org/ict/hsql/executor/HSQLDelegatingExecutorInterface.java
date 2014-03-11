package org.ict.hsql.executor;
import org.ict.hsql.exception.HSQLException;

import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-14
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public interface HSQLDelegatingExecutorInterface {
    public void init(String confPath) throws HSQLException;
    public boolean execute(String sql) throws HSQLException;
    public ResultSet getLastResult() throws  HSQLException;
    public ResultSet executeQuery(String sql) throws HSQLException;
    public int executeUpdate(String sql) throws HSQLException;
    public void close() throws HSQLException;
}
