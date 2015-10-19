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

import org.terracotta.exception.EntityAlreadyExistsException;
import org.terracotta.exception.EntityNotFoundException;
import org.terracotta.exception.EntityNotProvidedException;
import org.terracotta.exception.EntityVersionMismatchException;


/**
 * @param <T> The entity type underlying this reference.
 * @param <C> The configuration type to use when creating this entity.
 */
public interface EntityRef<T extends Entity, C> {
  /**
   * Create the entity with the given configuration
   *
   * @param configuration configuration to be applied to the entity
   */
  void create(C configuration) throws EntityNotProvidedException, EntityAlreadyExistsException, EntityVersionMismatchException;

  /**
   * Destroy the entity pointed to by this reference.
   */
  void destroy() throws EntityNotProvidedException, EntityNotFoundException;

  /**
   * Gets the entity pointed to by this reference. Can return null if no entity exists
   *
   * @return entity
   */
  T fetchEntity() throws EntityNotFoundException, EntityVersionMismatchException;

  /**
   * Gets the name of the entity
   *
   * @return name
   */
  String getName();
}
