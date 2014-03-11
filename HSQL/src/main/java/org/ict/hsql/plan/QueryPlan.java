package org.ict.hsql.plan;

import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;

import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-20
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public interface QueryPlan {
    public boolean execute(HSQLConnection connection) throws HSQLException;
    public ResultSet getLastResult() throws HSQLException;
    public ResultSet executeQuery(HSQLConnection connection) throws HSQLException;
    public int executeUpdate(HSQLConnection connection) throws HSQLException;
}
