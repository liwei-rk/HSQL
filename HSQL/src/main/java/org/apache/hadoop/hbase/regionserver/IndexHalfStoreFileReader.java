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

import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.KeyValue.Type;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.FSDataInputStreamWrapper;
import org.apache.hadoop.hbase.io.Reference;
import org.apache.hadoop.hbase.io.hfile.CacheConfig;
import org.apache.hadoop.hbase.io.hfile.HFileScanner;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * A facade for a {@link org.apache.hadoop.hbase.io.hfile.HFile.Reader} that serves up either the
 * top or bottom half of a HFile where 'bottom' is the first half of the file containing the keys
 * that sort lowest and 'top' is the second half of the file with keys that sort greater than those
 * of the bottom half. The top includes the split files midkey, of the key that follows if it does
 * not exist in the file.
 * <p>
 * This type works in tandem with the {@link Reference} type. This class is used reading while
 * Reference is used writing.
 * <p>
 * This file is not splitable. Calls to {@link #midkey()} return null.
 */
// TODO check some implementations here. Seems buggy!!
public class IndexHalfStoreFileReader extends StoreFile.Reader {
    private static final int ROW_KEY_LENGTH = 2;
    private final boolean top;
    // This is the key we split around. Its the first possible entry on a row:
    // i.e. empty column and a timestamp of LATEST_TIMESTAMP.
    private final byte[] splitkey;
    private final byte[] splitRow;


    public IndexHalfStoreFileReader(FileSystem fs, Path path, FSDataInputStreamWrapper in, long size, CacheConfig cacheConf, Reference r,Configuration conf) throws IOException {
        super(fs, path, in, size, cacheConf, conf);
        this.splitkey=r.getSplitKey();
        this.splitRow = KeyValue.createKeyValueFromKey(splitkey).getRow();
        this.top=Reference.isTopFileRegion(r.getFileRegion());
    }

    /**
     * @param p
     * @param cacheConf
     * @param r
     * @throws IOException
     */



    public IndexHalfStoreFileReader(final FileSystem fs, final Path p, final CacheConfig cacheConf,
                                    final Reference r, Configuration conf) throws IOException {
        super(fs,p,cacheConf,conf);

        this.splitkey = r.getSplitKey();
        // Is it top or bottom half?
        this.top = Reference.isTopFileRegion(r.getFileRegion());
        // why use split Row instead of splitkey
        this.splitRow = KeyValue.createKeyValueFromKey(splitkey).getRow();
    }
    protected boolean isTop() {
        return this.top;
    }
    @Override
    public HFileScanner getScanner(final boolean cacheBlocks, final boolean pread,
                                   final boolean isCompaction) {
        final HFileScanner s = super.getScanner(cacheBlocks, pread, isCompaction);
        return new HFileScanner() {
            final HFileScanner delegate = s;
            public boolean atEnd = false;
            public ByteBuffer getKey() {
                if (atEnd) {
                    return null;
                }
                if (!top) {   //botom no need to change the startkey
                    return delegate.getKey();
                }
                // If it is top store file replace the StartKey of the Key with SplitKey
                return getChangedKey(delegate.getKeyValue(),splitRow);
            }

            private ByteBuffer getChangedKey(KeyValue kv,byte[] startkey) {
                // new KeyValue(row, family, qualifier, timestamp, type, value)
                byte[] newRowkey = new HIndexRecord().deserialize(kv.getRow()).setRegionStartKey(startkey).serialize();

                KeyValue newKv =
                        new KeyValue(newRowkey, kv.getFamily(), kv.getQualifier(), kv.getTimestamp(),
                                Type.codeToType(kv.getType()), null);
                ByteBuffer keyBuffer = ByteBuffer.wrap(newKv.getKey());
                return keyBuffer;
            }



            public String getKeyString() {
                if (atEnd) {
                    return null;
                }
                return Bytes.toStringBinary(getKey());
            }

            public ByteBuffer getValue() {
                if (atEnd) {
                    return null;
                }
                if (!top) {
                    return delegate.getValue();
                }
                // If it is top store file change the value corresponding to the changed key like
                // [first 2 bytes]StartKey length replace with SplitKey length
                // [last 2 bytes]ActualRowKey offset add with difference of SplitKey & StartKey
                //byte[] changedValue = getChangedValue(delegate.getKeyValue().getValue());
                //return ByteBuffer.wrap(changedValue);
                return ByteBuffer.wrap(HConstants.EMPTY_BYTE_ARRAY);
            }
            /*

            private byte[] getChangedValue(byte[] value) {
                if (value.length == 0) return value; // The value can be empty when the KV type is DELETE.
                int lenghtOfTheStartKey = Bytes.toShort(value, 0);
                int offsetOfActualKey = Bytes.toShort(value, 2);
                offsetOfActualKey = offsetOfActualKey + (splitRow.length - lenghtOfTheStartKey);
                byte[] changedValue = new byte[4];
                System
                        .arraycopy(Bytes.toBytes((short) splitRow.length), 0, changedValue, 0, ROW_KEY_LENGTH);
                System.arraycopy(Bytes.toBytes((short) offsetOfActualKey), 0, changedValue, ROW_KEY_LENGTH,
                        ROW_KEY_LENGTH);
                return changedValue;
            }

            */
            public String getValueString() {
                if (atEnd) {
                    return null;
                }
                return Bytes.toStringBinary(getValue());
            }

            public KeyValue getKeyValue() {
                if (atEnd) {
                    return null;
                }
                KeyValue kv = delegate.getKeyValue();
                if (!top) {
                    return kv;
                }
                // If it is a top store file change the StartKey with SplitKey in Key
                // and produce the new value corresponding to the change in key
                byte[] changedKey =getKey().array();
                byte[] changedValue = HConstants.EMPTY_BYTE_ARRAY;//getChangedValue(kv.getValue());
                KeyValue changedKv =
                        new KeyValue(changedKey, kv.getFamily(), kv.getQualifier(), kv.getTimestamp(),
                                Type.codeToType(kv.getType()), changedValue);
                return changedKv;
            }

            public boolean next() throws IOException {
                if (atEnd) {
                    return false;
                }
                // TODO check what will be returned when next moves the cursor to the last entry
                // in the file
                while (true) {
                    boolean b = delegate.next();
                    if (!b) {
                        atEnd = true;
                        return b;
                    }
                    // We need to check whether the current KV pointed by this reader is corresponding to
                    // this split or not.
                    // In case of top store file if the ActualRowKey >= SplitKey
                    // In case of bottom store file if the ActualRowKey < Splitkey
                    if (isSatisfiedMidKeyCondition(delegate.getKeyValue())) {
                        return true;
                    }
                }
            }

            public boolean seekBefore(byte[] key) throws IOException {
                return seekBefore(key, 0, key.length);
            }

            public boolean seekBefore(byte[] key, int offset, int length) throws IOException {
                if (top) {
                    byte[] fk = getFirstKey();
                    // This will be null when the file is empty in which we can not seekBefore to any key
                    if (fk == null) {
                        return false;
                    }
                    if (getComparator().compare(key, offset, length, fk, 0, fk.length) <= 0) {
                        return false;
                    }
                    KeyValue rkv=new KeyValue(key,offset,length);
                    KeyValue replacedKey = new KeyValue(getChangedKey(rkv,getFirstKey()).array());
                    return this.delegate.seekBefore(replacedKey.getBuffer(), replacedKey.getKeyOffset(),
                            replacedKey.getKeyLength());
                } else {
                    // The equals sign isn't strictly necessary just here to be consistent with seekTo
                    if (getComparator().compare(key, offset, length, splitkey, 0, splitkey.length) >= 0) {
                        return this.delegate.seekBefore(splitkey, 0, splitkey.length);
                    }
                }
                return this.delegate.seekBefore(key, offset, length);
            }

            public boolean seekTo() throws IOException {
                boolean b = delegate.seekTo();
                if (!b) {
                    atEnd = true;
                    return b;
                }
                while (true) {
                    // We need to check the first occurrence of satisfying the condition
                    // In case of top store file if the ActualRowKey >= SplitKey
                    // In case of bottom store file if the ActualRowKey < Splitkey
                    if (isSatisfiedMidKeyCondition(delegate.getKeyValue())) {
                        return true;
                    }
                    b = delegate.next();
                    if (!b) {
                        return b;
                    }
                }
            }

            public int seekTo(byte[] key) throws IOException {
                return seekTo(key, 0, key.length);
            }

            public int seekTo(byte[] key, int offset, int length) throws IOException {
                if (top) {
                    if (getComparator().compare(key, offset, length, splitkey, 0, splitkey.length) < 0) {
                        return -1;
                    }
                    KeyValue rkv=new KeyValue(key,offset,length);
                    KeyValue replacedKey = new KeyValue(getChangedKey(rkv,getFirstKey()).array());


                    int seekTo =
                            delegate.seekTo(replacedKey.getBuffer(), replacedKey.getKeyOffset(),
                                    replacedKey.getKeyLength());
                    return seekTo;
          /*
           * if (seekTo == 0 || seekTo == -1) { return seekTo; } else if (seekTo == 1) { boolean
           * next = this.next(); }
           */
                } else {
                    if (getComparator().compare(key, offset, length, splitkey, 0, splitkey.length) >= 0) {
                        // we would place the scanner in the second half.
                        // it might be an error to return false here ever...
                        boolean res = delegate.seekBefore(splitkey, 0, splitkey.length);
                        if (!res) {
                            throw new IOException(
                                    "Seeking for a key in bottom of file, but key exists in top of file, failed on seekBefore(midkey)");
                        }
                        return 1;
                    }
                }
                return delegate.seekTo(key, offset, length);
            }

            public int reseekTo(byte[] key) throws IOException {
                return reseekTo(key, 0, key.length);
            }

            public int reseekTo(byte[] key, int offset, int length) throws IOException {
                if (top) {
                    if (getComparator().compare(key, offset, length, splitkey, 0, splitkey.length) < 0) {
                        return -1;
                    }
                    KeyValue rkv=new KeyValue(key,offset,length);
                    KeyValue replacedKey = new KeyValue(getChangedKey(rkv,getFirstKey()).array());
                    return delegate.reseekTo(replacedKey.getBuffer(), replacedKey.getKeyOffset(),
                            replacedKey.getKeyLength());
                } else {
                    if (getComparator().compare(key, offset, length, splitkey, 0, splitkey.length) >= 0) {
                        // we would place the scanner in the second half.
                        // it might be an error to return false here ever...
                        boolean res = delegate.seekBefore(splitkey, 0, splitkey.length);
                        if (!res) {
                            throw new IOException(
                                    "Seeking for a key in bottom of file, but key exists in top of file, failed on seekBefore(midkey)");
                        }
                        return 1;
                    }
                }
                return delegate.reseekTo(key, offset, length);
            }

            public org.apache.hadoop.hbase.io.hfile.HFile.Reader getReader() {
                return this.delegate.getReader();
            }

            // TODO: Need to change as per IndexHalfStoreFileReader
            public boolean isSeeked() {
                return this.delegate.isSeeked();
            }
        };
    }

    private boolean isSatisfiedMidKeyCondition(KeyValue kv) {
        if (KeyValue.isDelete(kv.getType()) && kv.getValue().length == 0) {
            // In case of a Delete type KV, let it be going to both the daughter regions.
            // No problems in doing so. In the correct daughter region where it belongs to, this delete
            // tomb will really delete a KV. In the other it will just hang around there with no actual
            // kv coming for which this is a delete tomb. :)
            return true;
        }
        byte[] row = kv.getRow();
        int offsetToActuRowKey = Bytes.toShort(kv.getValue(), ROW_KEY_LENGTH);
        int actuRowKeyLength = (row.length - offsetToActuRowKey);
        byte[] actuRowKey = new byte[actuRowKeyLength];
        System.arraycopy(row, offsetToActuRowKey, actuRowKey, 0, actuRowKeyLength);
        int compareResult = Bytes.compareTo(actuRowKey, splitRow);
        if (top) {
            if (compareResult >= 0) {
                return true;
            }
        } else {
            if (compareResult < 0) {
                return true;
            }
        }
        return false;
    }

    public byte[] getLastKey() {
        // This method wont get used for the index region. There is no need to call getClosestRowBefore
        // on the index table. Also this is a split region. Can not be further split
        throw new UnsupportedOperationException("Method is not implemented!");
    }


    public byte[] midkey() throws IOException {
        // Returns null to indicate file is not splitable.
        return null;
    }

    @Override
    public byte[] getFirstKey() {
        return super.getFirstKey();
    }

    @Override
    public boolean passesKeyRangeFilter(Scan scan) {
        return true;
    }
}
