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

import java.util.Collections;
import java.util.Set;

/**
 * Implements the simplest concurrency strategy possible: use the same key for everything to force serial execution
 * in the same queue.
 *
 * @author twu
 */
public class NoConcurrencyStrategy<M extends EntityMessage> implements ConcurrencyStrategy<M> {
  
  public NoConcurrencyStrategy() {
  }

  @Override
  public int concurrencyKey(M payload) {
    return ConcurrencyStrategy.MANAGEMENT_KEY;
  }

  @Override
  public Set<Integer> getKeysForSynchronization() {
    // XXX: We temporarily disable synchronization of all users of this strategy until we determine if it needs to be special.
    return Collections.emptySet();
  }
}
