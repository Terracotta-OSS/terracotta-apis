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
package org.terracotta.exception;


/**
 * This specific EntityException type is thrown in cases where an entity failed to be created because an entity with that
 * class and name already exists.
 */
public class EntityAlreadyExistsException extends EntityException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates the exception instance describing the given type-name pair.
   * 
   * @param className The name of the entity type
   * @param entityName The name of the entity instance
   */
  public EntityAlreadyExistsException(String className, String entityName) {
    super(className, entityName, "already exists", null);
  }

  /**
   * Creates the exception instance describing the given type-name pair along with underlying cause of this exception
   *
   * @param className The name of the entity type
   * @param entityName The name of the entity instance
   * @param cause underlying cause of this exception
   */
  public EntityAlreadyExistsException(String className, String entityName, Throwable cause) {
    super(className, entityName, "already exists", cause);
  }
}
