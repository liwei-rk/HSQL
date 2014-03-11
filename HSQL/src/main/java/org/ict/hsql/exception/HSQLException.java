package org.ict.hsql.exception;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-14
 * Time: 下午4:07
 * To change this template use File | Settings | File Templates.
 */
public class HSQLException extends SQLException{
    public HSQLException(HSQLExceptionCode code){
        this(code,code.toString());
    }
    public HSQLException(HSQLExceptionCode code,String reason) {
        super(reason);
        this.code=code;
    }
    public enum HSQLExceptionCode{UNDEFINED,NOT_SUPPORT,INVALID_SQL,IO}
    @Override
    public int getErrorCode() {
        return code.ordinal();   //To change body of overridden methods use File | Settings | File Templates.
    }
    HSQLExceptionCode code;
}
