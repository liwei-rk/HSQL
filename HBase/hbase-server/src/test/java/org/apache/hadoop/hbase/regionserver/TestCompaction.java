/**
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

import static org.apache.hadoop.hbase.HBaseTestingUtility.START_KEY;
import static org.apache.hadoop.hbase.HBaseTestingUtility.START_KEY_BYTES;
import static org.apache.hadoop.hbase.HBaseTestingUtility.fam1;
import static org.apache.hadoop.hbase.HBaseTestingUtility.fam2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HBaseTestCase.HRegionIncommon;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MediumTests;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.io.hfile.HFileDataBlockEncoder;
import org.apache.hadoop.hbase.io.hfile.HFileDataBlockEncoderImpl;
import org.apache.hadoop.hbase.io.hfile.HFileScanner;
import org.apache.hadoop.hbase.regionserver.compactions.CompactionContext;
import org.apache.hadoop.hbase.regionserver.compactions.CompactionProgress;
import org.apache.hadoop.hbase.regionserver.compactions.CompactionRequest;
import org.apache.hadoop.hbase.regionserver.compactions.DefaultCompactor;
import org.apache.hadoop.hbase.regionserver.compactions.RatioBasedCompactionPolicy;
import org.apache.hadoop.hbase.regionserver.wal.HLog;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.hbase.util.Threads;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


/**
 * Test compactions
 */
@Category(MediumTests.class)
public class TestCompaction {
  @Rule public TestName name = new TestName();
  static final Log LOG = LogFactory.getLog(TestCompaction.class.getName());
  private static final HBaseTestingUtility UTIL = new HBaseTestingUtility().createLocalHTU();
  protected Configuration conf = UTIL.getConfiguration();
  
  private HRegion r = null;
  private HTableDescriptor htd = null;
  private Path compactionDir = null;
  private Path regionCompactionDir = null;
  private static final byte [] COLUMN_FAMILY = fam1;
  private final byte [] STARTROW = Bytes.toBytes(START_KEY);
  private static final byte [] COLUMN_FAMILY_TEXT = COLUMN_FAMILY;
  private int compactionThreshold;
  private byte[] firstRowBytes, secondRowBytes, thirdRowBytes;
  final private byte[] col1, col2;
  private static final long MAX_FILES_TO_COMPACT = 10;

  /** constructor */
  public TestCompaction() {
    super();

    // Set cache flush size to 1MB
    conf.setInt(HConstants.HREGION_MEMSTORE_FLUSH_SIZE, 1024*1024);
    conf.setInt("hbase.hregion.memstore.block.multiplier", 100);
    compactionThreshold = conf.getInt("hbase.hstore.compactionThreshold", 3);

    firstRowBytes = START_KEY_BYTES;
    secondRowBytes = START_KEY_BYTES.clone();
    // Increment the least significant character so we get to next row.
    secondRowBytes[START_KEY_BYTES.length - 1]++;
    thirdRowBytes = START_KEY_BYTES.clone();
    thirdRowBytes[START_KEY_BYTES.length - 1] += 2;
    col1 = Bytes.toBytes("column1");
    col2 = Bytes.toBytes("column2");
  }

  @Before
  public void setUp() throws Exception {
    this.htd = UTIL.createTableDescriptor(name.getMethodName());
    this.r = UTIL.createLocalHRegion(htd, null, null);
  }

  @After
  public void tearDown() throws Exception {
    HLog hlog = r.getLog();
    this.r.close();
    hlog.closeAndDelete();
  }

  /**
   * Test that on a major compaction, if all cells are expired or deleted, then
   * we'll end up with no product.  Make sure scanner over region returns
   * right answer in this case - and that it just basically works.
   * @throws IOException
   */
  @Test
  public void testMajorCompactingToNoOutput() throws IOException {
    createStoreFile(r);
    for (int i = 0; i < compactionThreshold; i++) {
      createStoreFile(r);
    }
    // Now delete everything.
    InternalScanner s = r.getScanner(new Scan());
    do {
      List<Cell> results = new ArrayList<Cell>();
      boolean result = s.next(results);
      r.delete(new Delete(CellUtil.cloneRow(results.get(0))));
      if (!result) break;
    } while(true);
    s.close();
    // Flush
    r.flushcache();
    // Major compact.
    r.compactStores(true);
    s = r.getScanner(new Scan());
    int counter = 0;
    do {
      List<Cell> results = new ArrayList<Cell>();
      boolean result = s.next(results);
      if (!result) break;
      counter++;
    } while(true);
    assertEquals(0, counter);
  }

  /**
   * Run compaction and flushing memstore
   * Assert deletes get cleaned up.
   * @throws Exception
   */
  @Test
  public void testMajorCompaction() throws Exception {
    majorCompaction();
  }

  @Test
  public void testDataBlockEncodingInCacheOnly() throws Exception {
    majorCompactionWithDataBlockEncoding(true);
  }

  @Test
  public void testDataBlockEncodingEverywhere() throws Exception {
    majorCompactionWithDataBlockEncoding(false);
  }

  public void majorCompactionWithDataBlockEncoding(boolean inCacheOnly)
      throws Exception {
    Map<HStore, HFileDataBlockEncoder> replaceBlockCache =
        new HashMap<HStore, HFileDataBlockEncoder>();
    for (Entry<byte[], Store> pair : r.getStores().entrySet()) {
      HStore store = (HStore) pair.getValue();
      HFileDataBlockEncoder blockEncoder = store.getDataBlockEncoder();
      replaceBlockCache.put(store, blockEncoder);
      final DataBlockEncoding inCache = DataBlockEncoding.PREFIX;
      final DataBlockEncoding onDisk = inCacheOnly ? DataBlockEncoding.NONE :
          inCache;
      store.setDataBlockEncoderInTest(new HFileDataBlockEncoderImpl(onDisk));
    }

    majorCompaction();

    // restore settings
    for (Entry<HStore, HFileDataBlockEncoder> entry :
        replaceBlockCache.entrySet()) {
      entry.getKey().setDataBlockEncoderInTest(entry.getValue());
    }
  }

  private void majorCompaction() throws Exception {
    createStoreFile(r);
    for (int i = 0; i < compactionThreshold; i++) {
      createStoreFile(r);
    }
    // Add more content.
    HBaseTestCase.addContent(new HRegionIncommon(r), Bytes.toString(COLUMN_FAMILY));

    // Now there are about 5 versions of each column.
    // Default is that there only 3 (MAXVERSIONS) versions allowed per column.
    //
    // Assert == 3 when we ask for versions.
    Result result = r.get(new Get(STARTROW).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100));
    assertEquals(compactionThreshold, result.size());

    // see if CompactionProgress is in place but null
    for (Store store : this.r.stores.values()) {
      assertNull(store.getCompactionProgress());
    }

    r.flushcache();
    r.compactStores(true);

    // see if CompactionProgress has done its thing on at least one store
    int storeCount = 0;
    for (Store store : this.r.stores.values()) {
      CompactionProgress progress = store.getCompactionProgress();
      if( progress != null ) {
        ++storeCount;
        assertTrue(progress.currentCompactedKVs > 0);
        assertTrue(progress.totalCompactingKVs > 0);
      }
      assertTrue(storeCount > 0);
    }

    // look at the second row
    // Increment the least significant character so we get to next row.
    byte [] secondRowBytes = START_KEY_BYTES.clone();
    secondRowBytes[START_KEY_BYTES.length - 1]++;

    // Always 3 versions if that is what max versions is.
    result = r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).
        setMaxVersions(100));
    LOG.debug("Row " + Bytes.toStringBinary(secondRowBytes) + " after " +
        "initial compaction: " + result);
    assertEquals("Invalid number of versions of row "
        + Bytes.toStringBinary(secondRowBytes) + ".", compactionThreshold,
        result.size());

    // Now add deletes to memstore and then flush it.
    // That will put us over
    // the compaction threshold of 3 store files.  Compacting these store files
    // should result in a compacted store file that has no references to the
    // deleted row.
    LOG.debug("Adding deletes to memstore and flushing");
    Delete delete = new Delete(secondRowBytes, System.currentTimeMillis());
    byte [][] famAndQf = {COLUMN_FAMILY, null};
    delete.deleteFamily(famAndQf[0]);
    r.delete(delete);

    // Assert deleted.
    result = r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100));
    assertTrue("Second row should have been deleted", result.isEmpty());

    r.flushcache();

    result = r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100));
    assertTrue("Second row should have been deleted", result.isEmpty());

    // Add a bit of data and flush.  Start adding at 'bbb'.
    createSmallerStoreFile(this.r);
    r.flushcache();
    // Assert that the second row is still deleted.
    result = r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100));
    assertTrue("Second row should still be deleted", result.isEmpty());

    // Force major compaction.
    r.compactStores(true);
    assertEquals(r.getStore(COLUMN_FAMILY_TEXT).getStorefiles().size(), 1);

    result = r.get(new Get(secondRowBytes).addFamily(COLUMN_FAMILY_TEXT).setMaxVersions(100));
    assertTrue("Second row should still be deleted", result.isEmpty());

    // Make sure the store files do have some 'aaa' keys in them -- exactly 3.
    // Also, that compacted store files do not have any secondRowBytes because
    // they were deleted.
    verifyCounts(3,0);

    // Multiple versions allowed for an entry, so the delete isn't enough
    // Lower TTL and expire to ensure that all our entries have been wiped
    final int ttl = 1000;
    for (Store hstore : this.r.stores.values()) {
      HStore store = ((HStore) hstore);
      ScanInfo old = store.getScanInfo();
      ScanInfo si = new ScanInfo(old.getFamily(),
          old.getMinVersions(), old.getMaxVersions(), ttl,
          old.getKeepDeletedCells(), 0, old.getComparator());
      store.setScanInfo(si);
    }
    Thread.sleep(1000);

    r.compactStores(true);
    int count = count();
    assertEquals("Should not see anything after TTL has expired", 0, count);
  }

  @Test
  public void testTimeBasedMajorCompaction() throws Exception {
    // create 2 storefiles and force a major compaction to reset the time
    int delay = 10 * 1000; // 10 sec
    float jitterPct = 0.20f; // 20%
    conf.setLong(HConstants.MAJOR_COMPACTION_PERIOD, delay);
    conf.setFloat("hbase.hregion.majorcompaction.jitter", jitterPct);

    HStore s = ((HStore) r.getStore(COLUMN_FAMILY));
    s.storeEngine.getCompactionPolicy().setConf(conf);
    try {
      createStoreFile(r);
      createStoreFile(r);
      r.compactStores(true);

      // add one more file & verify that a regular compaction won't work
      createStoreFile(r);
      r.compactStores(false);
      assertEquals(2, s.getStorefilesCount());

      // ensure that major compaction time is deterministic
      RatioBasedCompactionPolicy
          c = (RatioBasedCompactionPolicy)s.storeEngine.getCompactionPolicy();
      Collection<StoreFile> storeFiles = s.getStorefiles();
      long mcTime = c.getNextMajorCompactTime(storeFiles);
      for (int i = 0; i < 10; ++i) {
        assertEquals(mcTime, c.getNextMajorCompactTime(storeFiles));
      }

      // ensure that the major compaction time is within the variance
      long jitter = Math.round(delay * jitterPct);
      assertTrue(delay - jitter <= mcTime && mcTime <= delay + jitter);

      // wait until the time-based compaction interval
      Thread.sleep(mcTime);

      // trigger a compaction request and ensure that it's upgraded to major
      r.compactStores(false);
      assertEquals(1, s.getStorefilesCount());
    } finally {
      // reset the timed compaction settings
      conf.setLong(HConstants.MAJOR_COMPACTION_PERIOD, 1000*60*60*24);
      conf.setFloat("hbase.hregion.majorcompaction.jitter", 0.20F);
      // run a major to reset the cache
      createStoreFile(r);
      r.compactStores(true);
      assertEquals(1, s.getStorefilesCount());
    }
  }

  @Test
  public void testMinorCompactionWithDeleteRow() throws Exception {
    Delete deleteRow = new Delete(secondRowBytes);
    testMinorCompactionWithDelete(deleteRow);
  }

  @Test
  public void testMinorCompactionWithDeleteColumn1() throws Exception {
    Delete dc = new Delete(secondRowBytes);
    /* delete all timestamps in the column */
    dc.deleteColumns(fam2, col2);
    testMinorCompactionWithDelete(dc);
  }

  @Test
  public void testMinorCompactionWithDeleteColumn2() throws Exception {
    Delete dc = new Delete(secondRowBytes);
    dc.deleteColumn(fam2, col2);
    /* compactionThreshold is 3. The table has 4 versions: 0, 1, 2, and 3.
     * we only delete the latest version. One might expect to see only
     * versions 1 and 2. HBase differs, and gives us 0, 1 and 2.
     * This is okay as well. Since there was no compaction done before the
     * delete, version 0 seems to stay on.
     */
    //testMinorCompactionWithDelete(dc, 2);
    testMinorCompactionWithDelete(dc, 3);
  }

  @Test
  public void testMinorCompactionWithDeleteColumnFamily() throws Exception {
    Delete deleteCF = new Delete(secondRowBytes);
    deleteCF.deleteFamily(fam2);
    testMinorCompactionWithDelete(deleteCF);
  }

  @Test
  public void testMinorCompactionWithDeleteVersion1() throws Exception {
    Delete deleteVersion = new Delete(secondRowBytes);
    deleteVersion.deleteColumns(fam2, col2, 2);
    /* compactionThreshold is 3. The table has 4 versions: 0, 1, 2, and 3.
     * We delete versions 0 ... 2. So, we still have one remaining.
     */
    testMinorCompactionWithDelete(deleteVersion, 1);
  }

  @Test
  public void testMinorCompactionWithDeleteVersion2() throws Exception {
    Delete deleteVersion = new Delete(secondRowBytes);
    deleteVersion.deleteColumn(fam2, col2, 1);
    /*
     * the table has 4 versions: 0, 1, 2, and 3.
     * We delete 1.
     * Should have 3 remaining.
     */
    testMinorCompactionWithDelete(deleteVersion, 3);
  }

  /*
   * A helper function to test the minor compaction algorithm. We check that
   * the delete markers are left behind. Takes delete as an argument, which
   * can be any delete (row, column, columnfamliy etc), that essentially
   * deletes row2 and column2. row1 and column1 should be undeleted
   */
  private void testMinorCompactionWithDelete(Delete delete) throws Exception {
    testMinorCompactionWithDelete(delete, 0);
  }
  private void testMinorCompactionWithDelete(Delete delete, int expectedResultsAfterDelete) throws Exception {
    HRegionIncommon loader = new HRegionIncommon(r);
    for (int i = 0; i < compactionThreshold + 1; i++) {
      HBaseTestCase.addContent(loader, Bytes.toString(fam1), Bytes.toString(col1), firstRowBytes, thirdRowBytes, i);
      HBaseTestCase.addContent(loader, Bytes.toString(fam1), Bytes.toString(col2), firstRowBytes, thirdRowBytes, i);
      HBaseTestCase.addContent(loader, Bytes.toString(fam2), Bytes.toString(col1), firstRowBytes, thirdRowBytes, i);
      HBaseTestCase.addContent(loader, Bytes.toString(fam2), Bytes.toString(col2), firstRowBytes, thirdRowBytes, i);
      r.flushcache();
    }

    Result result = r.get(new Get(firstRowBytes).addColumn(fam1, col1).setMaxVersions(100));
    assertEquals(compactionThreshold, result.size());
    result = r.get(new Get(secondRowBytes).addColumn(fam2, col2).setMaxVersions(100));
    assertEquals(compactionThreshold, result.size());

    // Now add deletes to memstore and then flush it.  That will put us over
    // the compaction threshold of 3 store files.  Compacting these store files
    // should result in a compacted store file that has no references to the
    // deleted row.
    r.delete(delete);

    // Make sure that we have only deleted family2 from secondRowBytes
    result = r.get(new Get(secondRowBytes).addColumn(fam2, col2).setMaxVersions(100));
    assertEquals(expectedResultsAfterDelete, result.size());
    // but we still have firstrow
    result = r.get(new Get(firstRowBytes).addColumn(fam1, col1).setMaxVersions(100));
    assertEquals(compactionThreshold, result.size());

    r.flushcache();
    // should not change anything.
    // Let us check again

    // Make sure that we have only deleted family2 from secondRowBytes
    result = r.get(new Get(secondRowBytes).addColumn(fam2, col2).setMaxVersions(100));
    assertEquals(expectedResultsAfterDelete, result.size());
    // but we still have firstrow
    result = r.get(new Get(firstRowBytes).addColumn(fam1, col1).setMaxVersions(100));
    assertEquals(compactionThreshold, result.size());

    // do a compaction
    Store store2 = this.r.stores.get(fam2);
    int numFiles1 = store2.getStorefiles().size();
    assertTrue("Was expecting to see 4 store files", numFiles1 > compactionThreshold); // > 3
    ((HStore)store2).compactRecentForTestingAssumingDefaultPolicy(compactionThreshold);   // = 3
    int numFiles2 = store2.getStorefiles().size();
    // Check that we did compact
    assertTrue("Number of store files should go down", numFiles1 > numFiles2);
    // Check that it was a minor compaction.
    assertTrue("Was not supposed to be a major compaction", numFiles2 > 1);

    // Make sure that we have only deleted family2 from secondRowBytes
    result = r.get(new Get(secondRowBytes).addColumn(fam2, col2).setMaxVersions(100));
    assertEquals(expectedResultsAfterDelete, result.size());
    // but we still have firstrow
    result = r.get(new Get(firstRowBytes).addColumn(fam1, col1).setMaxVersions(100));
    assertEquals(compactionThreshold, result.size());
  }

  private void verifyCounts(int countRow1, int countRow2) throws Exception {
    int count1 = 0;
    int count2 = 0;
    for (StoreFile f: this.r.stores.get(COLUMN_FAMILY_TEXT).getStorefiles()) {
      HFileScanner scanner = f.getReader().getScanner(false, false);
      scanner.seekTo();
      do {
        byte [] row = scanner.getKeyValue().getRow();
        if (Bytes.equals(row, STARTROW)) {
          count1++;
        } else if(Bytes.equals(row, secondRowBytes)) {
          count2++;
        }
      } while(scanner.next());
    }
    assertEquals(countRow1,count1);
    assertEquals(countRow2,count2);
  }

  /**
   * Verify that you can stop a long-running compaction
   * (used during RS shutdown)
   * @throws Exception
   */
  @Test
  public void testInterruptCompaction() throws Exception {
    assertEquals(0, count());

    // lower the polling interval for this test
    int origWI = HStore.closeCheckInterval;
    HStore.closeCheckInterval = 10*1000; // 10 KB

    try {
      // Create a couple store files w/ 15KB (over 10KB interval)
      int jmax = (int) Math.ceil(15.0/compactionThreshold);
      byte [] pad = new byte[1000]; // 1 KB chunk
      for (int i = 0; i < compactionThreshold; i++) {
        HRegionIncommon loader = new HRegionIncommon(r);
        Put p = new Put(Bytes.add(STARTROW, Bytes.toBytes(i)));
        p.setDurability(Durability.SKIP_WAL);
        for (int j = 0; j < jmax; j++) {
          p.add(COLUMN_FAMILY, Bytes.toBytes(j), pad);
        }
        HBaseTestCase.addContent(loader, Bytes.toString(COLUMN_FAMILY));
        loader.put(p);
        loader.flushcache();
      }

      HRegion spyR = spy(r);
      doAnswer(new Answer() {
        public Object answer(InvocationOnMock invocation) throws Throwable {
          r.writestate.writesEnabled = false;
          return invocation.callRealMethod();
        }
      }).when(spyR).doRegionCompactionPrep();

      // force a minor compaction, but not before requesting a stop
      spyR.compactStores();

      // ensure that the compaction stopped, all old files are intact,
      Store s = r.stores.get(COLUMN_FAMILY);
      assertEquals(compactionThreshold, s.getStorefilesCount());
      assertTrue(s.getStorefilesSize() > 15*1000);
      // and no new store files persisted past compactStores()
      FileStatus[] ls = r.getFilesystem().listStatus(r.getRegionFileSystem().getTempDir());
      assertEquals(0, ls.length);

    } finally {
      // don't mess up future tests
      r.writestate.writesEnabled = true;
      HStore.closeCheckInterval = origWI;

      // Delete all Store information once done using
      for (int i = 0; i < compactionThreshold; i++) {
        Delete delete = new Delete(Bytes.add(STARTROW, Bytes.toBytes(i)));
        byte [][] famAndQf = {COLUMN_FAMILY, null};
        delete.deleteFamily(famAndQf[0]);
        r.delete(delete);
      }
      r.flushcache();

      // Multiple versions allowed for an entry, so the delete isn't enough
      // Lower TTL and expire to ensure that all our entries have been wiped
      final int ttl = 1000;
      for (Store hstore: this.r.stores.values()) {
        HStore store = (HStore)hstore;
        ScanInfo old = store.getScanInfo();
        ScanInfo si = new ScanInfo(old.getFamily(),
            old.getMinVersions(), old.getMaxVersions(), ttl,
            old.getKeepDeletedCells(), 0, old.getComparator());
        store.setScanInfo(si);
      }
      Thread.sleep(ttl);

      r.compactStores(true);
      assertEquals(0, count());
    }
  }

  private int count() throws IOException {
    int count = 0;
    for (StoreFile f: this.r.stores.
        get(COLUMN_FAMILY_TEXT).getStorefiles()) {
      HFileScanner scanner = f.getReader().getScanner(false, false);
      if (!scanner.seekTo()) {
        continue;
      }
      do {
        count++;
      } while(scanner.next());
    }
    return count;
  }

  private void createStoreFile(final HRegion region) throws IOException {
    createStoreFile(region, Bytes.toString(COLUMN_FAMILY));
  }

  private void createStoreFile(final HRegion region, String family) throws IOException {
    HRegionIncommon loader = new HRegionIncommon(region);
    HBaseTestCase.addContent(loader, family);
    loader.flushcache();
  }

  private void createSmallerStoreFile(final HRegion region) throws IOException {
    HRegionIncommon loader = new HRegionIncommon(region);
    HBaseTestCase.addContent(loader, Bytes.toString(COLUMN_FAMILY), ("" +
    		"bbb").getBytes(), null);
    loader.flushcache();
  }

  @Test
  public void testCompactionWithCorruptResult() throws Exception {
    int nfiles = 10;
    for (int i = 0; i < nfiles; i++) {
      createStoreFile(r);
    }
    HStore store = (HStore) r.getStore(COLUMN_FAMILY);

    Collection<StoreFile> storeFiles = store.getStorefiles();
    DefaultCompactor tool = (DefaultCompactor)store.storeEngine.getCompactor();

    List<Path> newFiles = tool.compactForTesting(storeFiles, false);

    // Now lets corrupt the compacted file.
    FileSystem fs = store.getFileSystem();
    // default compaction policy created one and only one new compacted file
    Path dstPath = store.getRegionFileSystem().createTempName();
    FSDataOutputStream stream = fs.create(dstPath, null, true, 512, (short)3, (long)1024, null);
    stream.writeChars("CORRUPT FILE!!!!");
    stream.close();
    Path origPath = store.getRegionFileSystem().commitStoreFile(
      Bytes.toString(COLUMN_FAMILY), dstPath);

    try {
      ((HStore)store).moveFileIntoPlace(origPath);
    } catch (Exception e) {
      // The complete compaction should fail and the corrupt file should remain
      // in the 'tmp' directory;
      assert (fs.exists(origPath));
      assert (!fs.exists(dstPath));
      System.out.println("testCompactionWithCorruptResult Passed");
      return;
    }
    fail("testCompactionWithCorruptResult failed since no exception was" +
        "thrown while completing a corrupt file");
  }

  /**
   * Test for HBASE-5920 - Test user requested major compactions always occurring
   */
  @Test
  public void testNonUserMajorCompactionRequest() throws Exception {
    Store store = r.getStore(COLUMN_FAMILY);
    createStoreFile(r);
    for (int i = 0; i < MAX_FILES_TO_COMPACT + 1; i++) {
      createStoreFile(r);
    }
    store.triggerMajorCompaction();

    CompactionRequest request = store.requestCompaction(Store.NO_PRIORITY, null).getRequest();
    assertNotNull("Expected to receive a compaction request", request);
    assertEquals(
      "System-requested major compaction should not occur if there are too many store files",
      false,
      request.isMajor());
  }

  /**
   * Test for HBASE-5920
   */
  @Test
  public void testUserMajorCompactionRequest() throws IOException{
    Store store = r.getStore(COLUMN_FAMILY);
    createStoreFile(r);
    for (int i = 0; i < MAX_FILES_TO_COMPACT + 1; i++) {
      createStoreFile(r);
    }
    store.triggerMajorCompaction();
    CompactionRequest request = store.requestCompaction(Store.PRIORITY_USER, null).getRequest();
    assertNotNull("Expected to receive a compaction request", request);
    assertEquals(
      "User-requested major compaction should always occur, even if there are too many store files",
      true, 
      request.isMajor());
  }

  /**
   * Create a custom compaction request and be sure that we can track it through the queue, knowing
   * when the compaction is completed.
   */
  @Test
  public void testTrackingCompactionRequest() throws Exception {
    // setup a compact/split thread on a mock server
    HRegionServer mockServer = Mockito.mock(HRegionServer.class);
    Mockito.when(mockServer.getConfiguration()).thenReturn(r.getBaseConf());
    CompactSplitThread thread = new CompactSplitThread(mockServer);
    Mockito.when(mockServer.getCompactSplitThread()).thenReturn(thread);

    // setup a region/store with some files
    Store store = r.getStore(COLUMN_FAMILY);
    createStoreFile(r);
    for (int i = 0; i < MAX_FILES_TO_COMPACT + 1; i++) {
      createStoreFile(r);
    }

    CountDownLatch latch = new CountDownLatch(1);
    TrackableCompactionRequest request = new TrackableCompactionRequest(latch);
    thread.requestCompaction(r, store, "test custom comapction", Store.PRIORITY_USER, request);
    // wait for the latch to complete.
    latch.await();

    thread.interruptIfNecessary();
  }

  /**
   * HBASE-7947: Regression test to ensure adding to the correct list in the
   * {@link CompactSplitThread}
   * @throws Exception on failure
   */
  @Test
  public void testMultipleCustomCompactionRequests() throws Exception {
    // setup a compact/split thread on a mock server
    HRegionServer mockServer = Mockito.mock(HRegionServer.class);
    Mockito.when(mockServer.getConfiguration()).thenReturn(r.getBaseConf());
    CompactSplitThread thread = new CompactSplitThread(mockServer);
    Mockito.when(mockServer.getCompactSplitThread()).thenReturn(thread);

    // setup a region/store with some files
    int numStores = r.getStores().size();
    List<Pair<CompactionRequest, Store>> requests =
        new ArrayList<Pair<CompactionRequest, Store>>(numStores);
    CountDownLatch latch = new CountDownLatch(numStores);
    // create some store files and setup requests for each store on which we want to do a
    // compaction
    for (Store store : r.getStores().values()) {
      createStoreFile(r, store.getColumnFamilyName());
      createStoreFile(r, store.getColumnFamilyName());
      createStoreFile(r, store.getColumnFamilyName());
      requests
          .add(new Pair<CompactionRequest, Store>(new TrackableCompactionRequest(latch), store));
    }

    thread.requestCompaction(r, "test mulitple custom comapctions", Store.PRIORITY_USER,
      Collections.unmodifiableList(requests));

    // wait for the latch to complete.
    latch.await();

    thread.interruptIfNecessary();
  }

  /**
   * Test that on a major compaction, if all cells are expired or deleted, then we'll end up with no
   * product. Make sure scanner over region returns right answer in this case - and that it just
   * basically works.
   * @throws IOException
   */
  public void testMajorCompactingToNoOutputWithReverseScan() throws IOException {
    createStoreFile(r);
    for (int i = 0; i < compactionThreshold; i++) {
      createStoreFile(r);
    }
    // Now delete everything.
    Scan scan = new Scan();
    scan.setReversed(true);
    InternalScanner s = r.getScanner(scan);
    do {
      List<Cell> results = new ArrayList<Cell>();
      boolean result = s.next(results);
      assertTrue(!results.isEmpty());
      r.delete(new Delete(results.get(0).getRow()));
      if (!result) break;
    } while (true);
    s.close();
    // Flush
    r.flushcache();
    // Major compact.
    r.compactStores(true);
    scan = new Scan();
    scan.setReversed(true);
    s = r.getScanner(scan);
    int counter = 0;
    do {
      List<Cell> results = new ArrayList<Cell>();
      boolean result = s.next(results);
      if (!result) break;
      counter++;
    } while (true);
    s.close();
    assertEquals(0, counter);
  }

  private class StoreMockMaker extends StatefulStoreMockMaker {
    public ArrayList<StoreFile> compacting = new ArrayList<StoreFile>();
    public ArrayList<StoreFile> notCompacting = new ArrayList<StoreFile>();
    private ArrayList<Integer> results;

    public StoreMockMaker(ArrayList<Integer> results) {
      this.results = results;
    }

    public class TestCompactionContext extends CompactionContext {
      private List<StoreFile> selectedFiles;
      public TestCompactionContext(List<StoreFile> selectedFiles) {
        super();
        this.selectedFiles = selectedFiles;
      }

      @Override
      public List<StoreFile> preSelect(List<StoreFile> filesCompacting) {
        return new ArrayList<StoreFile>();
      }

      @Override
      public boolean select(List<StoreFile> filesCompacting, boolean isUserCompaction,
          boolean mayUseOffPeak, boolean forceMajor) throws IOException {
        this.request = new CompactionRequest(selectedFiles);
        this.request.setPriority(getPriority());
        return true;
      }

      @Override
      public List<Path> compact() throws IOException {
        finishCompaction(this.selectedFiles);
        return new ArrayList<Path>();
      }
    }

    @Override
    public synchronized CompactionContext selectCompaction() {
      CompactionContext ctx = new TestCompactionContext(new ArrayList<StoreFile>(notCompacting));
      compacting.addAll(notCompacting);
      notCompacting.clear();
      try {
        ctx.select(null, false, false, false);
      } catch (IOException ex) {
        fail("Shouldn't happen");
      }
      return ctx;
    }

    @Override
    public synchronized void cancelCompaction(Object object) {
      TestCompactionContext ctx = (TestCompactionContext)object;
      compacting.removeAll(ctx.selectedFiles);
      notCompacting.addAll(ctx.selectedFiles);
    }

    public synchronized void finishCompaction(List<StoreFile> sfs) {
      if (sfs.isEmpty()) return;
      synchronized (results) {
        results.add(sfs.size());
      }
      compacting.removeAll(sfs);
    }

    @Override
    public int getPriority() {
      return 7 - compacting.size() - notCompacting.size();
    }
  }

  public class BlockingStoreMockMaker extends StatefulStoreMockMaker {
    BlockingCompactionContext blocked = null;

    public class BlockingCompactionContext extends CompactionContext {
      public volatile boolean isInCompact = false;

      public void unblock() {
        synchronized (this) { this.notifyAll(); }
      }

      @Override
      public List<Path> compact() throws IOException {
        try {
          isInCompact = true;
          synchronized (this) { this.wait(); }
        } catch (InterruptedException e) {
           Assume.assumeNoException(e);
        }
        return new ArrayList<Path>();
      }

      @Override
      public List<StoreFile> preSelect(List<StoreFile> filesCompacting) {
        return new ArrayList<StoreFile>();
      }

      @Override
      public boolean select(List<StoreFile> f, boolean i, boolean m, boolean e)
          throws IOException {
        this.request = new CompactionRequest(new ArrayList<StoreFile>());
        return true;
      }
    }

    @Override
    public CompactionContext selectCompaction() {
      this.blocked = new BlockingCompactionContext();
      try {
        this.blocked.select(null, false, false, false);
      } catch (IOException ex) {
        fail("Shouldn't happen");
      }
      return this.blocked;
    }

    @Override
    public void cancelCompaction(Object object) {}

    public int getPriority() {
      return Integer.MIN_VALUE; // some invalid value, see createStoreMock
    }

    public BlockingCompactionContext waitForBlocking() {
      while (this.blocked == null || !this.blocked.isInCompact) {
        Threads.sleepWithoutInterrupt(50);
      }
      BlockingCompactionContext ctx = this.blocked;
      this.blocked = null;
      return ctx;
    }

    @Override
    public Store createStoreMock(String name) throws Exception {
      return createStoreMock(Integer.MIN_VALUE, name);
    }

    public Store createStoreMock(int priority, String name) throws Exception {
      // Override the mock to always return the specified priority.
      Store s = super.createStoreMock(name);
      when(s.getCompactPriority()).thenReturn(priority);
      return s;
    }
  }

  /** Test compaction priority management and multiple compactions per store (HBASE-8665). */
  @Test
  public void testCompactionQueuePriorities() throws Exception {
    // Setup a compact/split thread on a mock server.
    final Configuration conf = HBaseConfiguration.create();
    HRegionServer mockServer = mock(HRegionServer.class);
    when(mockServer.isStopped()).thenReturn(false);
    when(mockServer.getConfiguration()).thenReturn(conf);
    CompactSplitThread cst = new CompactSplitThread(mockServer);
    when(mockServer.getCompactSplitThread()).thenReturn(cst);

    // Set up the region mock that redirects compactions.
    HRegion r = mock(HRegion.class);
    when(r.compact(any(CompactionContext.class), any(Store.class))).then(new Answer<Boolean>() {
      public Boolean answer(InvocationOnMock invocation) throws Throwable {
        ((CompactionContext)invocation.getArguments()[0]).compact();
        return true;
      }
    });

    // Set up store mocks for 2 "real" stores and the one we use for blocking CST.
    ArrayList<Integer> results = new ArrayList<Integer>();
    StoreMockMaker sm = new StoreMockMaker(results), sm2 = new StoreMockMaker(results);
    Store store = sm.createStoreMock("store1"), store2 = sm2.createStoreMock("store2");
    BlockingStoreMockMaker blocker = new BlockingStoreMockMaker();

    // First, block the compaction thread so that we could muck with queue.
    cst.requestSystemCompaction(r, blocker.createStoreMock(1, "b-pri1"), "b-pri1");
    BlockingStoreMockMaker.BlockingCompactionContext currentBlock = blocker.waitForBlocking();

    // Add 4 files to store1, 3 to store2, and queue compactions; pri 3 and 4 respectively.
    for (int i = 0; i < 4; ++i) {
      sm.notCompacting.add(createFile());
    }
    cst.requestSystemCompaction(r, store, "s1-pri3");
    for (int i = 0; i < 3; ++i) {
      sm2.notCompacting.add(createFile());
    }
    cst.requestSystemCompaction(r, store2, "s2-pri4");
    // Now add 2 more files to store1 and queue compaction - pri 1.
    for (int i = 0; i < 2; ++i) {
      sm.notCompacting.add(createFile());
    }
    cst.requestSystemCompaction(r, store, "s1-pri1");
    // Finally add blocking compaction with priority 2.
    cst.requestSystemCompaction(r, blocker.createStoreMock(2, "b-pri2"), "b-pri2");

    // Unblock the blocking compaction; we should run pri1 and become block again in pri2.
    currentBlock.unblock();
    currentBlock = blocker.waitForBlocking();
    // Pri1 should have "compacted" all 6 files.
    assertEquals(1, results.size());
    assertEquals(6, results.get(0).intValue());
    // Add 2 files to store 1 (it has 2 files now).
    for (int i = 0; i < 2; ++i) {
      sm.notCompacting.add(createFile());
    }
    // Now we have pri4 for store 2 in queue, and pri3 for store1; store1's current priority
    // is 5, however, so it must not preempt store 2. Add blocking compaction at the end.
    cst.requestSystemCompaction(r, blocker.createStoreMock(7, "b-pri7"), "b-pri7");
    currentBlock.unblock();
    currentBlock = blocker.waitForBlocking();
    assertEquals(3, results.size());
    assertEquals(3, results.get(1).intValue()); // 3 files should go before 2 files.
    assertEquals(2, results.get(2).intValue());

    currentBlock.unblock();
    cst.interruptIfNecessary();
  }

  private static StoreFile createFile() throws Exception {
    StoreFile sf = mock(StoreFile.class);
    when(sf.getPath()).thenReturn(new Path("file"));
    StoreFile.Reader r = mock(StoreFile.Reader.class);
    when(r.length()).thenReturn(10L);
    when(sf.getReader()).thenReturn(r);
    return sf;
  }

  /**
   * Simple {@link CompactionRequest} on which you can wait until the requested compaction finishes.
   */
  public static class TrackableCompactionRequest extends CompactionRequest {
    private CountDownLatch done;

    /**
     * Constructor for a custom compaction. Uses the setXXX methods to update the state of the
     * compaction before being used.
     */
    public TrackableCompactionRequest(CountDownLatch finished) {
      super();
      this.done = finished;
    }

    @Override
    public void afterExecute() {
      super.afterExecute();
      this.done.countDown();
    }
  }
}
