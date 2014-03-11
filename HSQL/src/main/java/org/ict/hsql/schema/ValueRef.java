package org.ict.hsql.schema;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-26
 * Time: 下午7:51
 * To change this template use File | Settings | File Templates.
 */
public class ValueRef {
    Object value;

    public ValueRef() {
    }

    public Object getValue(){
        return value;
    }
    public ValueRef setValue(Object value){
           this.value=value;
           return this;
    }

    public ValueRef(Object value) {
        this.value = value;
    }
}
