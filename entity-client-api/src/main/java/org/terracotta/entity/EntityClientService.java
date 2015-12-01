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
import org.terracotta.entity.EntityClientEndpoint;


/**
 * The specific service instance used to create client-side instances of the entities which wrap the connection to the
 * server.
 * 
 * @param <T> The client-side entity type
 * @param <C> The configuration type
 */
public interface EntityClientService<T extends Entity, C> {
  /**
   * Check if this service handles the given entity type.
   *
   * @param cls type to check
   * @return true if this service does handle the given type
   */
  boolean handlesEntityType(Class<T> cls);

  /**
   * Serialize the configuration for this entity type out to a byte array
   * 
   * @param configuration configuration to be serialized
   * @return serialized config
   */
  byte[] serializeConfiguration(C configuration);

  /**
   * Deserialize a configuration from bytes
   *
   * @param configuration bytes to be deserialized
   * @return deserialized config
   */
  C deserializeConfiguration(byte[] configuration);
  
  /**
   * Create an entity of the given type.
   *
   * @param endpoint RPC endpoint for the entity
   * @return entity
   */
  T create(EntityClientEndpoint endpoint);
}
