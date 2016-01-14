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
import java.util.Map;

import com.tc.classloader.CommonComponent;


/**
 * This interface describes the needs of Persistor.  An implementation of this interface can be used as the storage back-end
 * of an instance of Persistor.
 * 
 * NOTE:  Parts of this interface exist for demonstrations and tests of life-cycle and may be removed in the future (explicit
 * open()/create(), for example).
 */
@CommonComponent
public interface IPersistentStorage {
  /**
   * Called to open an existing storage instance for use.  The caller will expect any data previously present in the backing
   * store to be available.
   * 
   * Either open() or create() must be called before the instance can be used.
   * 
   * @throws IOException If there was a failure to open the storage or it couldn't be found.
   */
  public void open() throws IOException;
  
  /**
   * Called to create a new storage instance for use.  The caller will expect the storage to be empty (contain no existing
   * key-value storage instances).
   * 
   * Either open() or create() must be called before the instance can be used.
   * 
   * @throws IOException If there was a failure to create the storage or if it already existed.
   */
  public void create() throws IOException;
  
  /**
   * Closes the underlying storage, rendering the receiver unavailable for any future operations.
   * Calls made to the receiver or any objects it has created, after this call returns, are undefined.
   */
  public void close();
  
  public Transaction begin();  

  public Map<String, String> getProperties();

  public <K, V> KeyValueStorage<K, V> getKeyValueStorage(String alias, Class<K> keyClass, Class<V> valueClass);
  
  public <K, V> KeyValueStorage<K, V> createKeyValueStorage(String alias, Class<K> keyClass, Class<V> valueClass);
  
  public <K, V> KeyValueStorage<K, V> destroyKeyValueStorage(String alias);
  
  public interface Transaction {
    void commit();
    
    void abort();
  }
}
