package org.ict.coprocessor;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.ict.coprocessor.protocol.DeleteRowsProtocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-2
 * Time: 下午12:28
 * To change this template use File | Settings | File Templates.
 */

public class DeleteRowsEndpoint{ /* extends BaseEndpointCoprocessor implements DeleteRowsProtocol {
    public long delete(FilterList filters) throws IOException{
        RegionCoprocessorEnvironment environment = (RegionCoprocessorEnvironment) getEnvironment();
        HRegion hRegion = environment.getRegion();
        //abc

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

                Delete d = new Delete(curVals.get(0).getRow());
                hRegion.delete(d, true);
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

