package org.ict.coprocessor;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.ict.coprocessor.protocol.UpdateRowsProtocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-4
 * Time: 上午10:58
 * To change this template use File | Settings | File Templates.
 */
public class UpdateRowsEndpoint {/* extends BaseEndpointCoprocessor implements UpdateRowsProtocol {
    public long update(Put u, FilterList filters) {
        RegionCoprocessorEnvironment environment = (RegionCoprocessorEnvironment) getEnvironment();
        HRegion hRegion = environment.getRegion();
        Scan scan = new Scan();
        if (!filters.getFilters().isEmpty()) {
            scan.setFilter(filters);
        }
        int result = 0;
        InternalScanner internalScanner;
        try {
            internalScanner = hRegion.getScanner(scan);
        } catch (IOException e) {
            return result;
        }
        try {
            List<KeyValue> curVals = new ArrayList<KeyValue>();
            boolean done = false;
            do {
                curVals.clear();
                done = internalScanner.next(curVals);
                Put p = new Put(curVals.get(0).getRow());
                for (byte[] family : u.getFamilyMap().keySet()) {
                    for (KeyValue kv : u.getFamilyMap().get(family)) {
                        p.add(kv.getFamily(), kv.getQualifier(), kv.getValue());
                    }
                }
                hRegion.put(p);
                result += 1;
            } while (done);
        } catch (IOException e) {
            try {
                internalScanner.close();
            } catch (IOException ie) {
            }
        }
        return result;
    }
    */
}
