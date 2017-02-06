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

import org.terracotta.connection.entity.Entity;


/**
 * <p>The specific service instance used to create client-side instances of the entities which wrap the connection to the
 * server.</p>
 * 
 * <p>A client-side entity is created in response to every successful
 * {@link org.terracotta.connection.entity.EntityRef#fetchEntity() EntityRef.fetchEntity()} call, each of which is given
 * a unique {@link EntityClientEndpoint}.</p>
 * 
 * <p>Conceptually, the endpoint can be considered much like a file descriptor:  multiple attempts to open the same
 * file (or fetch the same entity) result in multiple unique descriptors which must be closed, independently, when no
 * longer required.</p>
 * 
 * @param <T> The client-side {@link Entity} type
 * @param <C> The configuration type
 * @param <M> An {@link EntityMessage}
 * @param <R> An {@link EntityResponse}
 */
public interface EntityClientService<T extends Entity, C, M extends EntityMessage, R extends EntityResponse> {
  /**
   * Check if this service handles the given entity type.
   *
   * @param cls type to check
   * @return true if this service does handle the given type
   */
  boolean handlesEntityType(Class<T> cls);

  /**
   * Serialize the configuration for this entity type out to a byte array.
   * 
   * @param configuration Configuration object to be serialized
   * @return Serialized configuration object
   */
  byte[] serializeConfiguration(C configuration);

  /**
   * Deserialize a configuration from bytes.
   *
   * @param configuration Bytes to be deserialized
   * @return Deserialized configuration object
   */
  C deserializeConfiguration(byte[] configuration);

  /**
   * <p>Create an entity of the given type to be built over the given {@link EntityClientEndpoint}.</p>
   * 
   * <p>Note that the given endpoint is unique to this instance (calling
   * {@link org.terracotta.connection.entity.EntityRef#fetchEntity() EntityRef.fetchEntity()} multiple times will result
   * in multiple calls to this method, each with a unique endpoint instance).<p>
   * 
   * <p>The given endpoint is "owned by" the object this method returns.  When
   * {@link org.terracotta.connection.entity.Entity#close()} is called, it is expected to also close this endpoint.</p>
   *
   * @param endpoint The RPC endpoint for interacting with the server-side entity represented by the returned instance.
   * @return The client-side representation of the fetched server entity.
   */
  T create(EntityClientEndpoint<M, R> endpoint);

  /**
   * Gets the message codec which will be used to convert high-level {@link EntityMessage}/{@link EntityResponse}
   * to byte[] and vice-versa.
   *
   * @return The {@link org.terracotta.entity.MessageCodec MessageCodec} for this entity type
   */
  MessageCodec<M, R> getMessageCodec();
}
