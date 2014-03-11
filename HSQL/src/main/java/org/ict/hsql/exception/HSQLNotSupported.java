package org.ict.hsql.exception;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class HSQLNotSupported extends HSQLException {
    static String reason = "Not Supported yet!";

    public HSQLNotSupported() {
        super(HSQLExceptionCode.NOT_SUPPORT, reason);
    }
}
