package org.ict.coprocessor.protocol;


import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-17
 * Time: 下午3:29
 * To change this template use File | Settings | File Templates.
 */
public interface RowCountProtocol{ //extends CoprocessorProtocol {
    long getRowCount() throws IOException;
}
