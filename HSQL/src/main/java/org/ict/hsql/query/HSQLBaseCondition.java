package org.ict.hsql.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-5
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public abstract class HSQLBaseCondition implements HSQLCondition {
    List<HSQLCondition> conds;
    public HSQLBaseCondition() {
        conds=new ArrayList<HSQLCondition>();
    }
}
