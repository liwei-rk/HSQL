package org.apache.hadoop.hbase.regionserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.catalog.MetaEditor;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.io.FSDataInputStreamWrapper;
import org.apache.hadoop.hbase.io.Reference;
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.PairOfSameType;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.WritableUtils;
import org.ict.coprocessor.ChangeMetaDataEndpoint;
import org.ict.hsql.schema.HIndex;
import org.ict.hsql.schema.TableSchema;
import org.ict.hsql.schema.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 14-2-13
 * Time: 下午6:57
 * To change this template use File | Settings | File Templates.
 */
public class IndexRegionObserver extends BaseRegionObserver {
    Log LOG = LogFactory.getLog(IndexRegionObserver.class);
    boolean isCatalogRegion = false;
    boolean isIndexTable=false;
    Configuration conf=null;




    @Override
    public void preOpen(ObserverContext<RegionCoprocessorEnvironment> e) throws IOException {
        super.preOpen(e);    //To change body of overridden methods use File | Settings | File Templates.
        isIndexTable=IndexUtils.isIndexTable(e.getEnvironment().getRegion().getTableDesc().getName());
        isCatalogRegion = IndexUtils.isCatalogTable(e.getEnvironment().getRegion().getTableDesc().getName());
        if(isCatalogRegion) {
            LOG.info("is CatalogRegion "+e.getEnvironment().getRegion().getTableDesc().getNameAsString());
        }
        conf=e.getEnvironment().getConfiguration();


    }

    @Override
    public StoreFile.Reader preStoreFileReaderOpen(ObserverContext<RegionCoprocessorEnvironment> ctx, FileSystem fs, Path p, FSDataInputStreamWrapper in, long size, CacheConfig cacheConf, Reference r, StoreFile.Reader reader) throws IOException {
        if(r!=null&&isIndexTable){
           ctx.complete();
        return new IndexHalfStoreFileReader(fs,p,in,size,cacheConf,r,conf);
        }else{
            return null;
        }
    }

    @Override
    public RegionScanner preScannerOpen(ObserverContext<RegionCoprocessorEnvironment> e, Scan scan, RegionScanner s) throws IOException {
        //best define custom Filter by myself
        if (isCatalogRegion) return null;
        Filter filter = scan.getFilter();


        if (filter == null || !(filter instanceof FilterList)) {
            return null;
        }
        LOG.fatal("liwei is here before schema");

        TableSchema schema = ChangeMetaDataEndpoint.schemaCache.get(e.getEnvironment().getRegion().getTableDesc().getNameAsString());
        if (schema.getIndexInfos().size() == 0) return null;
        LOG.fatal("liwei is here,after schema");
        FilterList fl = (FilterList) filter;
        List<Filter> filterList = fl.getFilters();
        if (filterList.size() == 0) return null;
        if (!(filterList.get(0) instanceof SingleColumnValueFilter)) {
            return null;
        } else {
            SingleColumnValueFilter sf = (SingleColumnValueFilter) filterList.get(0);
            LOG.fatal("sf is "+sf);
            if (schema.getIndexInfos().containsKey(new HIndex(Bytes.toString(sf.getQualifier())))) {
                HRegion userRegion = e.getEnvironment().getRegion();
                HRegion indexRegion = getIndexRegion(e.getEnvironment().getRegionServerServices(), userRegion.getStartKey(), IndexUtils.getIndexTableName(userRegion.getTableDesc().getNameAsString()));
                if(indexRegion==null) return null;
                e.bypass();
                RegionScanner r=new IndexRegionScanner(sf, userRegion, indexRegion,scan);
                LOG.info("region scanner is"+r);
                return r;
            } else {
                return null;
            }
        }
    }

    private HRegion getIndexRegion(RegionServerServices rs, byte[] startKey, String indexTableName)
            throws IOException {
        List<HRegion> indexTabRegions = rs.getOnlineRegions(TableName.valueOf(indexTableName));
        for (HRegion indexRegion : indexTabRegions) {
            if (Bytes.equals(startKey, indexRegion.getStartKey())) {
                return indexRegion;
            }
        }
        return null;
    }

    @Override
    public void preSplitAfterPONR(ObserverContext<RegionCoprocessorEnvironment> ctx) throws IOException {
        super.preSplitAfterPONR(ctx);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void preBatchMutate(ObserverContext<RegionCoprocessorEnvironment> c, MiniBatchOperationInProgress<Mutation> miniBatchOp) throws IOException {
        RegionServerServices rs = c.getEnvironment().getRegionServerServices();
        HRegion userRegion = c.getEnvironment().getRegion();
        HTableDescriptor userTableDesc = userRegion.getTableDesc();
        String tableName = userTableDesc.getNameAsString();
        String indexTableName = IndexUtils.getIndexTableName(tableName);
        if (IndexUtils.isCatalogTable(userTableDesc.getName()) || IndexUtils.isIndexTable(tableName)) {
            return;
        }
        TableSchema schema = ChangeMetaDataEndpoint.schemaCache.get(tableName);
        if (schema == null || schema.getIndexInfos().size() == 0) {
            return;
        }
        LOG.fatal("current table is " + tableName);
        LOG.fatal(schema.toString());
        MapWritable indexInfos = schema.getIndexInfos();
        LOG.info(indexInfos.toString());

        LOG.trace("Entering preBatchMutate for the table " + tableName);
        LOG.trace("Indices for the table " + tableName + " are: " + indexInfos);
        HRegion indexRegion = getIndexRegion(rs, userRegion.getStartKey(), indexTableName);
        if (indexRegion == null) {
            LOG.fatal("indexRegion is null");
            return;
        } else {
            LOG.fatal("indexName is" + indexRegion.getRegionNameAsString());
        }
        // Storing this found HRegion in the index table within the thread locale.
        for (int i = 0; i < miniBatchOp.size(); i++) {
            LOG.fatal("mini size is" + miniBatchOp.size());
            Mutation m = miniBatchOp.getOperation(i);
            if (m instanceof Put) {
                List<Put> puts = prepareIndexPutMutations(indexInfos, userRegion, (Put) m, tableName, indexRegion);
                for (Put p : puts) {
                    LOG.fatal("puts size is" + puts.size());
                    indexRegion.put(p);
                    LOG.fatal("ok sleep");
                }
            }
        }
        LOG.trace("Exiting preBatchMutate for the table " + tableName);
    }

    private List<Put> prepareIndexPutMutations(MapWritable indexInfos, HRegion userRegion, Put m, String tableName, HRegion indexRegion) {
        // indexInfos.
        List<Put> puts = new ArrayList<Put>();

        Map<byte[], List<Cell>> family = m.getFamilyCellMap();
        for (byte[] key : family.keySet()) {
            List<Cell> cells = family.get(key);
            for (Cell cell : cells) {
                byte[] q = cell.getQualifier();
                HIndex i = new HIndex(Bytes.toString(q));
                if (indexInfos.containsKey(i)) {  //not consider cf
                    puts.add(mkIndexPutRow(indexRegion, cell, i));
                }
            }
        }
        return puts;
    }

    private Put mkIndexPutRow(HRegion indexRegion, Cell m, HIndex index) {
        HIndexRecord record = new HIndexRecord(indexRegion.getStartKey(), m, index);
        LOG.info(record.toString() + "liwei");
        Put p = new Put(record.serialize());
        LOG.fatal("index row key size is:"+p.getRow().length);
        p.add(Bytes.toBytes("i"), HConstants.EMPTY_START_ROW, HConstants.EMPTY_START_ROW);
        return p;
    }


    @Override
    public void preSplitBeforePONR(ObserverContext<RegionCoprocessorEnvironment> ctx, byte[] splitKey, List<Mutation> metaEntries) throws IOException {

        RegionCoprocessorEnvironment environment = ctx.getEnvironment();
        LOG.info("liwei");
        RegionServerServices rs = environment.getRegionServerServices();
        HRegion region = environment.getRegion();
        String userTableName = region.getTableDesc().getNameAsString();
        LOG.trace("Entering preSplitBeforePONR for the table " + userTableName + " for the region "
                + region.getRegionInfo());
        String indexTableName = Utils.getIndexTableName(userTableName);
        TableSchema schema = ChangeMetaDataEndpoint.schemaCache.get(userTableName);
        if (rs.getOnlineRegions(TableName.valueOf(indexTableName)).size() == 0) {
        } else {
            HRegion indexRegion;
            SplitTransaction st = null;
            try {
                indexRegion = getIndexRegion(rs, region.getStartKey(), indexTableName);
                if (null != indexRegion) {
                    LOG.info("Flushing the cache for the index table " + indexTableName + " for the region "
                            + indexRegion.getRegionInfo());
                    indexRegion.flushcache();
                    if (LOG.isInfoEnabled()) {
                        LOG.info("Forcing split for the index table " + indexTableName + " with split key "
                                + Bytes.toString(splitKey));
                    }
                    st = new SplitTransaction(indexRegion, splitKey);
                    if (!st.prepare()) {
                        LOG.error("Prepare for the index table " + indexTableName
                                + " failed. So returning null. ");
                        return;
                    }
                    indexRegion.forceSplit(splitKey);
                    PairOfSameType<HRegion> daughterRegions = st.stepsBeforePONR(rs, rs, false);
                    LOG.info("Daughter regions created for the index table " + indexTableName
                            + " for the region " + indexRegion.getRegionInfo());
                    MetaEditor.splitRegion(rs.getCatalogTracker(), indexRegion.getRegionInfo(), daughterRegions.getFirst().getRegionInfo(), daughterRegions.getSecond().getRegionInfo(), rs.getServerName());
                    st.stepsAfterPONR(rs, rs, daughterRegions);
                } else {
                    LOG.error("IndexRegion for the table " + indexTableName + " is null. So returning null. ");
                }
            } catch (Exception ex) {
                st.rollback(rs, rs);
            }
        }
    }

    @Override
    public RegionScanner postScannerOpen(ObserverContext<RegionCoprocessorEnvironment> e, Scan scan, RegionScanner s) throws IOException {
        return super.postScannerOpen(e, scan, s);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
