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

package org.terracotta.connection.entity;

/**
 * @author twu
 */
public interface EntityMaintenanceRef<T extends Entity, C> extends AutoCloseable {
  /**
   * @return True if the underlying entity actually exists.
   */
  boolean doesExist();
  
  /**
   * Destroy the entity pointed to by this reference.
   */
  void destroy();

  /**
   * Create the entity with the given configuration
   *
   * @param configuration configuration to be applied to the entity
   */
  void create(C configuration);

  /**
   * Release this maintenance mode reference.
   */
  @Override
  void close();
}
