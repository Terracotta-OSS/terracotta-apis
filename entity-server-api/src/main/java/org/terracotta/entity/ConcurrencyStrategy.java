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

package org.terracotta.entity;

import java.util.Set;

/**
 * The concurrency strategy is used to manage and control this entity.  
 * @param <M> The message type expected by the implementation.
 */
public interface ConcurrencyStrategy<M extends EntityMessage> {
  /**
   * UNIVERSAL_KEY is a negative key that indicates a request requires no order and can be run concurrently with any
   * other entity operation
   */
  public final int UNIVERSAL_KEY = Integer.MIN_VALUE; 
  /**
   * MANAGEMENT_KEY is a zero key which is used to manage the entity (anything other than invoke action).
   */
  public final int MANAGEMENT_KEY = 0; 
  /**
   * Given a payload for an entity request, return an integer key. The server will guarantee that requests with
   * the same key greater than or equal to zero will run linearly. It does not, however, guarantee that requests 
   * with different keys will run on different threads (i.e. it's perfectly valid for the server to ignore the key and
   * put everything on a single thread).
   * 
   * Any negative key is considered invalid and can be executed concurrently with any other entity request
   *
   * The zero key is used for management of the entity.  If zero is returned as the key, execution will run serially to 
   * platform management of the entity.
   * 
   * At a minimum this method needs to be thread-safe. Ideally, it should be a pure function.
   *
   * @param message The message from the client.
   * @return integer key 
   */
  int concurrencyKey(M message);

  /**
   * Called when the platform wishes to synchronize the data and state of an active server entity to a newly-online passive
   * server.  This operation is performed on a per-concurrencyKey basis so this method is used to determine the set of keys
   * which must be synchronized.
   * 
   * @return The set of concurrency keys to synchronize to the passive.
   */
  Set<Integer> getKeysForSynchronization();
}
