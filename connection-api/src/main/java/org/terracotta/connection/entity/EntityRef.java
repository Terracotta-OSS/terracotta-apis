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
   * @throws EntityNotProvidedException The service providing T doesn't exist on either the client or the server
   * @throws EntityAlreadyExistsException An entity with this type and name already exists
   * @throws EntityVersionMismatchException No entity exists but the client and server providing services for T don't have the same version numbers
   */
  void create(C configuration) throws EntityNotProvidedException, EntityAlreadyExistsException, EntityVersionMismatchException;

  /**
   * Destroy the entity pointed to by this reference.
   * 
   * @throws EntityNotProvidedException The service providing T doesn't exist on either the client or the server
   * @throws EntityNotFoundException No entity with this type and name could be found
   */
  void destroy() throws EntityNotProvidedException, EntityNotFoundException;

  /**
   * Gets the entity pointed to by this reference. Can return null if no entity exists
   *
   * @return entity
   * @throws EntityNotFoundException No entity with this type and name could be found
   * @throws EntityVersionMismatchException The entity exists but the client and server providing services for T don't have the same version numbers
   */
  T fetchEntity() throws EntityNotFoundException, EntityVersionMismatchException;

  /**
   * Gets the name of the entity
   *
   * @return name
   */
  String getName();
}
