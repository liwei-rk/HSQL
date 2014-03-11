package org.ict.hsql.executor;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午7:28
 * To change this template use File | Settings | File Templates.
 */
public class HSQLExecutorContext {
    public enum ISOLATION_LEVEL{NONE,CONSISTENCE}
    ISOLATION_LEVEL isolation_level;

    public void setIsolation_level(ISOLATION_LEVEL isolation_level) {
        this.isolation_level = isolation_level;
    }

    public ISOLATION_LEVEL getIsolation_level() {

        return isolation_level;
    }

    public HSQLExecutorContext(ISOLATION_LEVEL isolation_level) {

        this.isolation_level = isolation_level;
    }
}
