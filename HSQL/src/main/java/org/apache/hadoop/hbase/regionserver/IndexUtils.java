/**
 * Copyright 2011 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.regionserver;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.FSUtils;
import org.ict.hsql.schema.Constants;
import org.ict.hsql.schema.SchemaConstants;

public class IndexUtils {

  private static final Log LOG = LogFactory.getLog(IndexUtils.class);

  private static final String DOT_TABLEINFO = ".tableinfo";

  public static final String TABLE_INPUT_COLS = "table.columns.index";

  /**
   * Utility method to get the name of the index table when given the name of the actual table.
   * @param tableName
   * @return index table name
   */
  public static String getIndexTableName(String tableName) {
    // TODO The suffix for the index table is fixed now. Do we allow to make this configurable?
    // We can handle things in byte[] way?
    return tableName + Constants.INDEX_TABLE_SUFFIX;
  }

  /**
   * Utility method to get the name of the index table when given the name of the actual table.
   * @param tableName
   * @return index table name
   */
  public static String getIndexTableName(byte[] tableName) {
    return getIndexTableName(Bytes.toString(tableName));
  }

  /**
   * Tells whether the passed table is a secondary index table or a normal table.
   * @param tableName
   * @return
   */
  public static boolean isIndexTable(String tableName) {
    return tableName.endsWith(Constants.INDEX_TABLE_SUFFIX);
  }

  /**
   * Tells whether the passed table is a secondary index table or a normal table.
   * @param tableName
   * @return
   */
  public static boolean isIndexTable(byte[] tableName) {
    return isIndexTable(Bytes.toString(tableName));
  }

  /**
   * Checks whether the passed table is a catalog table or not
   * @param tableName
   * @return true when the passed table is a catalog table.
   */
  public static boolean isCatalogTable(byte[] tableName) {
    String tn=Bytes.toString(tableName);
    return tn.equals("hbase:meta") || tn.equals("hbase:namespace")||tn.equals(SchemaConstants.SCHEMATABLE);
  }






  // TODO check this... Is this ok with all cases?
  // No.. for -ve issues... Will see later..
  public static byte[] incrementValue(byte[] value, boolean copy) {
    byte[] newValue = new byte[value.length];
    if (copy) {
      System.arraycopy(value, 0, newValue, 0, newValue.length);
    } else {
      newValue = value;
    }
    for (int i = newValue.length - 1; i >= 0; i--) {
      byte b = newValue[i];
      b = (byte) (b + 1);
      if (b == 0) {
        newValue[i] = 0;
      } else {
        newValue[i] = b;
        break;
      }
    }
    return newValue;
  }

  public static String getActualTableNameFromIndexTableName(String indexTableName) {
    String split[] = indexTableName.split(Constants.INDEX_TABLE_SUFFIX);
    return split[0];
  }





  /**
   * Compare {@link org.apache.hadoop.fs.FileStatus} instances by {@link org.apache.hadoop.fs.Path#getName()}. Returns in reverse order.
   */
  static class FileStatusFileNameComparator implements Comparator<FileStatus> {
    @Override
    public int compare(FileStatus left, FileStatus right) {
      return -left.compareTo(right);
    }
  }




  private static KeyValue selectKVForIndexing(List<KeyValue> values) {
    KeyValue kv = null;
    long ts = HConstants.OLDEST_TIMESTAMP;
    for (KeyValue value : values) {
      // When the TS is same, then we need to consider the last KV
      // appearing in the KVList
      // as this will be added to the memstore with highest memstore TS.
      if (value.getTimestamp() >= ts) {
        kv = value;
        ts = value.getTimestamp();
      }
    }
    return kv;
  }

}
