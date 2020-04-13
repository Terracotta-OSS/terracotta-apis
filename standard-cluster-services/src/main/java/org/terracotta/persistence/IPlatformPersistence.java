/*
 *
 *  The contents of this file are subject to the Terracotta Public License Version
 *  2.0 (the "License"); You may not use this file except in compliance with the
 *  License. You may obtain a copy of the License at
 *
 *  http://terracotta.org/legal/terracotta-public-license.
 *
 *  Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 *  the specific language governing rights and limitations under the License.
 *
 *  The Covered Software is Entity API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.persistence;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;



/**
 * This interface defines the basic persistence requirements of the platform.  An implementation of this interface can be
 *  used as the storage back-end for a restartable server.
 * Some parts of the interface may also be generically useful for smaller entities, as well, since it defines common
 *  key-value storage concepts.
 * It is expected that an implementation will back-end, very directly, onto a filesystem.  Correspondingly, some methods
 *  are documented to require filesystem-safe names for data elements, in case the implementation wants to write them to
 *  named files (without the need for an explicit index).  This further allows the use of small, atomically-written files to
 *  provide naturally high-performance paths through otherwise generically-structured data, backed by a simple
 *  implementation.
 */
public interface IPlatformPersistence {
  /**
   * Returns the data previously stored for name or null if no such data was stored.
   * NOTE:  Any required classes will be loaded into the system class loader.
   * 
   * @param name The name of the data element (must be filesystem-safe)
   * @return The deserialized object, or null if there is no such data element
   * @throws IOException If something went wrong while interacting with the persistence medium
   */
  public Serializable loadDataElement(String name) throws IOException;

  /**
   * Returns the data previously stored for name or null if no such data was stored.
   * NOTE:  Any required classes will be loaded into the provided class loader.
   * 
   * @param name The name of the data element (must be filesystem-safe)
   * @param loader The class loader into which any required classes should be loaded
   * @return The deserialized object, or null if there is no such data element
   * @throws IOException If something went wrong while interacting with the persistence medium
   */
  public Serializable loadDataElementInLoader(String name, ClassLoader loader) throws IOException;

  /**
   * Stores the given element for the given name.
   * Note that the write is done atomically.
   * If element is null, any previous version of the data is deleted.
   * 
   * @param name The name of the data element (must be filesystem-safe)
   * @param element The data to store (null if the previous data should be deleted)
   * @throws IOException If something went wrong while interacting with the persistence medium
   */
  public void storeDataElement(String name, Serializable element) throws IOException;

  /**
   * A specialized mechanism which exposes a high-performance persistence stream for very simple data.  This is specifically
   *  tailored to the requirement of storing transaction order data from different clients but is presented in a slightly
   *  more generalized shape:  there are a given sequence of identifiers which are valid within a given index.  They are
   *  always added in an increasing order so they are implicitly sorted as they are enqueued.  At any point, values older
   *  than a certain value can be discarded as being no longer valid.
   * The entries added are a tuple of IDs:  one local and one global.  Only the local is consulted when purging now-invalid
   *  data.
   * The method returns a future which will return when the write has been made durable in the implementation's backing
   *  store.
   * Note that this method doesn't throw an exception since it is expected to be passed back via the returned Future's
   *  ExecutionException.
   * 
   * @param sequenceIndex The list of sequences to operate on
   * @param newEntry The new value to enter into the sequence
   * @param oldestValidSequenceID Any localSequenceIDs predating this can now be safely discarded
   * @return A Future which will return once the write for this operation has been made durable
   */
  public Future<Void> fastStoreSequence(long sequenceIndex, SequenceTuple newEntry, long oldestValidSequenceID);

  /**
   * Loads the given sequence of tuples previously written for the given sequenceIndex.
   * 
   * @param sequenceIndex The sequence to load
   * @return The list of tuples for the named sequenceIndex
   * @throws IOException If something went wrong while interacting with the persistence medium
   */
  public List<SequenceTuple> loadSequence(long sequenceIndex) throws IOException;

  /**
   * Deletes a given sequenceIndex from the backing store.
   * 
   * @param sequenceIndex The sequence to delete
   * @throws IOException If something went wrong while interacting with the persistence medium
   */
  public void deleteSequence(long sequenceIndex) throws IOException;


  /**
   * The tuples stored in sequence, on disk.
   * Note that only the localSequenceID is consulted to determine when to mark entries as invalid.  The globalSequenceID is
   *  essentially opaque meta-data.
   */
  public class SequenceTuple {
    public long localSequenceID;
    public long globalSequenceID;
  }
}
