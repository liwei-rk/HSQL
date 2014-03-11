package org.ict.coprocessor;

import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.schema.SchemaConstants;
import org.ict.hsql.schema.Utils;
import org.ict.hsql.utils.HSQLPropertyConstants;


import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-17
 * Time: 上午11:49
 * To change this template use File | Settings | File Templates.
 */
public class BaseRegionObserverExample extends BaseRegionObserver {
    public static final byte[] FIXED_ROW = Bytes.toBytes("@@@GET_TIME@@@");
    Log LOG=LogFactory.getLog(BaseRegionObserverExample.class)   ;
    @Override
    public void preSplit(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        super.preSplit(e);    //To change body of overridden methods use File | Settings | File Templates.
        RegionCoprocessorEnvironment env=e.getEnvironment();
        if(Utils.isIndexTable(env.getRegion().getTableDesc().getNameAsString())){
            e.bypass();
        }
    }

    @Override
    public void preOpen(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        super.preOpen(e);    //To change body of overridden methods use File | Settings | File Templates.
        RegionCoprocessorEnvironment env=e.getEnvironment() ;
        if(!env.getRegion().getTableDesc().isRootRegion()&&!env.getRegion().getTableDesc().isMetaRegion()&&!env.getRegion().getTableDesc().getNameAsString().equals(SchemaConstants.SCHEMATABLE)){
            if(env.getRegion().getTableDesc().getNameAsString().equals("hbase:namespace"))  return;
            LOG.error(env.getRegion().getTableDesc().getNameAsString());
                ChangeMetaDataEndpoint.readOrigionSchema(e.getEnvironment());
        }
    }
}
