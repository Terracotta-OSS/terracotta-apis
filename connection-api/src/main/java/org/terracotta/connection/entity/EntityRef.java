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
import org.terracotta.exception.EntityException;
import org.terracotta.exception.EntityNotFoundException;
import org.terracotta.exception.EntityNotProvidedException;
import org.terracotta.exception.EntityVersionMismatchException;


/**
 * The EntityRef is a reference to where an entity could potentially exist.
 * <P>
 *   That is, it is created to refer to a specific entity type and name, although there may not yet be an entity with
 *   that type and name.
 * </P>
 * <P>
 *   The EntityRef is used to manage the server-side entity's life cycle in that it is how the entity is either created
 *   or destroyed.
 * </P>
 * <P>
 *   It is also used as the path through which an actual instance can be fetched for use on the client.
 * </P>
 *
 * @param <T> The entity type underlying this reference.
 * @param <C> The configuration type to use when creating this entity.
 */
public interface EntityRef<T extends Entity, C> {
  /**
   * Creates the entity with the given configuration.
   * <P>
   *   If the entity already exists, the {@link EntityAlreadyExistsException} is  thrown immediately, whether or not
   *   the entity is fetched on any client.
   * </P>
   * <P>
   *   If a {@code create} is invoked while another {@code create} is happening, it will block until either:
   *   <UL>
   *     <LI>the other create completes and then throw an {@code EntityAlreadyExistsException}</LI>
   *     <LI>the other create fails and then it will (try to) perform the create as well</LI>
   *   </UL>
   *   The contract provides guarantee that when multiple clients race to {@code create}, and in the absence of any
   *   {@link #destroy()} or server failure, when the {@code create} method completes (returns or throws), a subsequent
   *   {@link #fetchEntity()} will not throw an {@link EntityNotFoundException}.
   * </P>
   *
   * @param configuration configuration to be applied to the entity
   *
   * @throws EntityNotProvidedException The service providing {@code <T>} doesn't exist on the server
   * @throws EntityAlreadyExistsException An entity with this type and name already exists
   * @throws EntityVersionMismatchException The client and server providing services for T don't have the same version numbers
   */
  void create(C configuration) throws EntityNotProvidedException, EntityAlreadyExistsException, EntityVersionMismatchException;

  /**
   * reconfigure the entity with the given configuration.
   *
   * @param configuration configuration to be applied to the entity
   * @throws EntityNotProvidedException The service providing T doesn't exist on either the client or the server
   */
  C reconfigure(C configuration) throws EntityException;

  /**
   * Destroys the entity pointed to by this reference if it is not fetched on any client.
   * <P>
   *   If the entity is fetched on any client, this method returns false.
   * </P>
   *
   * @return {@code true} if the entity was destroyed, {@code false} if there are fetched entities on any client
   * preventing destruction

   * @throws EntityNotProvidedException The service providing {@code <T>} doesn't exist on the server
   * @throws EntityNotFoundException No entity with this type and name could be found
   */
  boolean destroy() throws EntityNotProvidedException, EntityNotFoundException;

  /**
   * Gets the entity pointed to by this reference, or throws if the operation cannot complete.
   * <P>
   *   Multiple clients can hold a fetched reference at the same time.
   * </P>
   * <P>
   *   Note that the returned instance is in an "open" state and must be closed (using {@link Entity#close()}) to
   *   release this hold on the server-side instance. Otherwise, attempts to {@link #destroy()} it will fail.
   * </P>
   *
   * @return The client-side entity attached to the server-side instance
   *
   * @throws EntityNotFoundException No entity with this type and name could be found
   * @throws EntityVersionMismatchException The client and server providing services for T don't have the same version numbers
   */
  T fetchEntity() throws EntityNotFoundException, EntityVersionMismatchException;

  /**
   * Gets the name of the entity
   *
   * @return name
   */
  String getName();
}
