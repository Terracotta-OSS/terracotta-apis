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
 * @param <A> The specific ActiveServerEntity type created
 * @param <P> The specific PassiveServerEntity type created
 */
public interface ServerEntityService<A extends ActiveServerEntity<?>, P extends PassiveServerEntity<?>> {
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
  A createActiveEntity(ServiceRegistry registry, byte[] configuration);

  /**
   * Create an instance of the specified server entity in passive mode.
   *
   * @param registry registry of services provided by the server
   * @param configuration entity specific configuration object
   * @return server side entity
   */
  P createPassiveEntity(ServiceRegistry registry, byte[] configuration);
}
