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
 * The specific service instance used to create server-side instances of the given active and passive entity types.
 * 
 * @param <M> Incoming message type
 * @param <R> outgoing message type
 */
public interface EntityServerService<M extends EntityMessage, R extends EntityResponse> {
  /**
   * Used for ensuring that the entity version implementation version is consistent between server and client,
   * active and passive, and pre-restart and post-restart.
   * There is no support for partially-compatible versions.  If there is a mismatch here, it is an absolute
   * incompatibility.
   * Note that the version is expected to be a positive integer (> 0).
   * 
   * @return The version of the entity's implementation.
   */
  long getVersion();

  /**
   * Check if this service handles the given type
   *
   * @param typeName name of the type to check
   * @return true if this service works with the given type
   */
  boolean handlesEntityType(String typeName);

  /**
   * Create an instance of the specified server entity in active mode.
   *
   * @param registry registry of services provided by the server
   * @param configuration entity specific configuration object
   * @return server side entity
   */
  ActiveServerEntity<M, R> createActiveEntity(ServiceRegistry registry, byte[] configuration);

  /**
   * Create an instance of the specified server entity in passive mode.
   *
   * @param registry registry of services provided by the server
   * @param configuration entity specific configuration object
   * @return server side entity
   */
  PassiveServerEntity<M, R> createPassiveEntity(ServiceRegistry registry, byte[] configuration);
  
  /**
   * Reconfigure an existing entity during server execution.  Reconfigure call in the
   * platform's entity API allows an entity to reconfigure in place, its instance in the 
   * system.  Prior to the call of this method, the entities execution queue is flushed and 
   * any subsequent messages are blocked until this call completes.  This call occurs only 
   * under the MANAGEMENT_KEY concurrency key.  Implementations are allowed to either reconfigure
   * in place and return the same instance of the entity or a new entity may be built if desired.
   * 
   * @param <AP> the entity type being reconfigured
   * @param registry the service registry for the entity
   * @param oldEntity the instance of the entity implementation being reconfigured
   * @param configuration the new configuration of the entity
   * @return An entity instance influenced by the new configuration
   */
  default <AP extends CommonServerEntity<M, R>> AP reconfigureEntity(ServiceRegistry registry, AP oldEntity, byte[] configuration) {
    if (oldEntity instanceof PassiveServerEntity) {
      return (AP)createPassiveEntity(registry, configuration);
    } else if (oldEntity instanceof ActiveServerEntity) {
      return (AP)createActiveEntity(registry, configuration);
    } else {
      throw new AssertionError("unknown entity type");
    }
  }
  
  /**
   * Get the concurrency strategy to be used for this server entity.
   *
   * @param configuration
   * @return concurrency strategy
   */
  ConcurrencyStrategy<M> getConcurrencyStrategy(byte[] configuration);
  /**
   * Get the execution strategy to be used for this server entity.  The default implementation
   * designates that all messages will be run on both active and passive
   *
   * @return execution strategy
   */
  default ExecutionStrategy<M> getExecutionStrategy(byte[] configuration) {
    return new ExecutionStrategy<M>() {
      @Override
      public ExecutionStrategy.Location getExecutionLocation(M message) {
        return ExecutionStrategy.Location.IGNORE;
      }
    };
  }
  /**
   * Gets the message codec which will be used to convert high-level {@link EntityMessage}/{@link EntityResponse}
   * to byte[] and vice-versa
   *
   * @return message codec
   */
  MessageCodec<M, R> getMessageCodec();

  /**
   * Gets the Sync message codec which will be used to convert high-level Passive Sync Messages to byte[] and vice-versa
   *
   * @return A {@link org.terracotta.entity.SyncMessageCodec<M>}
   */
  SyncMessageCodec<M> getSyncMessageCodec();
}
