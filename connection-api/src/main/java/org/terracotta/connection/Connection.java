/*
 * Copyright Terracotta, Inc.
 * Copyright IBM Corp. 2024, 2025
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terracotta.connection;

import java.io.Closeable;
import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityRef;
import org.terracotta.exception.EntityNotProvidedException;


/**
 * Represents a connection to a cluster
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
   * @param <C> configuration type
   * @param <U> user data type for EntityClientService
   * @return reference to the entity
   * @throws EntityNotProvidedException There is no client-side service providing entities of type T
   */
  <T extends Entity, C, U> EntityRef<T, C, U> getEntityRef(Class<T> cls, long version, String name) throws EntityNotProvidedException;
  /**
   *
   * @return true if the connection is valid to remote resources
   */
  boolean isValid();
}
