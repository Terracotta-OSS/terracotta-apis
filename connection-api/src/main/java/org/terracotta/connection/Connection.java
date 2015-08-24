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
 *  The Covered Software is Connection API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */

package org.terracotta.connection;

import java.io.Closeable;
import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityMaintenanceRef;
import org.terracotta.connection.entity.EntityRef;

import java.util.Collection;

/**
 * Represents a connection to a cluster
 *
 * @author twu
 */
public interface Connection extends Closeable {
  /**
   * Get a reference to the given entity. The entity may or may not yet exist. This is a pointer to the spot where the
   * entity would be if it did exist.
   *
   * @param cls entity class
   * @param version version of the entity implementation
   * @param name name of the entity
   * @param <T> entity type
   * @return reference to the entity
   */
  <T extends Entity, C> EntityRef<T, C> getEntityRef(Class<T> cls, long version, String name);

  /**
   * Grab a maintenance mode reference to the specified entity of given type. This reference grants exclusive access.
   * Attempting to get a maintenance mode reference while outstanding holds exist will cause maintenance mode reference
   * acquisition to block.
   *
   * @param cls entity class
   * @param version version of the entity implementation
   * @param name name of the entity
   * @param <T> entity type
   * @return exclusive reference to the entity
   */
  <T extends Entity, C> EntityMaintenanceRef<T, C> acquireMaintenanceModeRef(Class<T> cls, long version, String name);
}
