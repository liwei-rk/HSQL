package org.ict.hsql;

import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.executor.HSQLDelegatingExecutor;
import org.ict.hsql.executor.HSQLExecutorContext;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public class HSQLDelegatingExecutorFactory {
    public static HSQLDelegatingExecutor create() throws HSQLException {
        HSQLExecutorContext context=new HSQLExecutorContext(HSQLExecutorContext.ISOLATION_LEVEL.NONE);
        return new HSQLDelegatingExecutor(context);
    }
}
