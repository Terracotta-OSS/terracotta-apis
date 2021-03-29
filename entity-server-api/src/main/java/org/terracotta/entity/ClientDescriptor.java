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

/**
 * An opaque token representing a specific entity instance on a specific client node.
 * This is used by server-side code to specifically communicate with or track connection
 * status of a specific client-side object instance. This is used both in actives
 * and passives in certain cases.
 * Note that implementations are expected to provide a proper equals()/hashCode().
 */
public interface ClientDescriptor {

  /**
   * Client source id for this client descriptor.
   * @return
   */
  ClientSourceId getSourceId();
  /**
   *
   * @return true if the @see ClientSourceId associated with this descriptor is a valid
   * remote client and not associated with a server call
   */
  default boolean isValidClient() {
    return getSourceId().isValidClient();
  }
}
