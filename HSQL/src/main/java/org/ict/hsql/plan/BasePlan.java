package org.ict.hsql.plan;

import org.apache.hadoop.hbase.filter.Filter;
import org.ict.hsql.connection.HSQLConnection;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.exception.HSQLNotSupported;
import org.ict.hsql.schema.ColumnValueRef;
import org.ict.hsql.schema.TableSchema;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-20
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasePlan implements QueryPlan {
    @Override
    public boolean execute(HSQLConnection connection) throws HSQLException {
        throw  new HSQLNotSupported();
    }

    @Override
    public ResultSet getLastResult() throws HSQLException {
        throw new HSQLNotSupported();
    }

    @Override
    public ResultSet executeQuery(HSQLConnection connection) throws HSQLException {
        throw new HSQLNotSupported();
    }

    @Override
    public int executeUpdate(HSQLConnection connection) throws HSQLException {
        throw new HSQLNotSupported();
    }

    enum STATTYPE{CREATE,DROP,DELETE}
    String tableName;
    TableSchema schemaInfo;
    Filter cond;
    List<ColumnValueRef> update;

    protected BasePlan() {
        this.prop =new HashMap<String, Object>();
    }

    String origionSQL;
    protected STATTYPE type;
    protected Map<String,Object> prop;
    public STATTYPE getType() throws HSQLException{
          return type;
    }
    public void setProperty(String key,Object value){
            prop.put(key,value);
    }
    abstract void checkValidProperty(HSQLConnection connection) throws HSQLException;
}
