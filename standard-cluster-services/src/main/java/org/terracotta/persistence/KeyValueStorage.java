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

import java.util.Collection;
import java.util.Set;

public interface KeyValueStorage<K, V> {
  
  // We can probably get rid of these
  @Deprecated
  public Set<K> keySet(); // only used for loading root mbeans

  @Deprecated
  public Collection<V> values(); // only used for retrieving GC roots,
  // maybe we can just make the entire root
  // map a single value to avoid this.

  @Deprecated
  public long size(); // only really used for stats logging

  // TODO: what exception to catch when
  public void put(K key, V value);

  public void put(K key, V value, byte metadata);

  public V get(K key);

  public boolean remove(K key);

  public void removeAll(Collection<K> keys);

  public boolean containsKey(K key);

  public void clear();
}