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
 * A Runtime exception that wraps exception(s) thrown from Server entity implementations
 */
public class EntityServerException extends EntityException {
  /**
   * All of these exception types have a specific description and refer to a type and name pair.
   *
   * @param className   The name of the type of the entity
   * @param entityName  The name of the entity instance
   * @param description The description describing the specific problem
   * @param cause       The underlying cause of the exception, null if nothing appropriate
   */
  public EntityServerException(final String className, final String entityName, final String description, final Throwable cause) {
    super(className, entityName, description, cause);
  }
}
