/*
 * Copyright Terracotta, Inc.
 * Copyright Super iPaaS Integration LLC, an IBM Company 2024
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
 * The base class of the exception hierarchy used by the entity API.
 * On its own, doesn't convey the meaning of the exceptional circumstance.  To determine and handle these specific
 * situations, catch the more specific sub-class exceptions.
 */
public abstract class EntityException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String className;
  private final String entityName;
  private final String description;

  public String getClassName() {
    return className;
  }

  public String getEntityName() {
    return entityName;
  }

  public String getDescription() {
    return description;
  }

  /**
   * All of these exception types have a specific description and refer to a type and name pair.
   * 
   * @param className The name of the type of the entity
   * @param entityName The name of the entity instance
   * @param description The description describing the specific problem
   * @param cause The underlying cause of the exception, null if nothing appropriate
   */
  protected EntityException(String className, String entityName, String description, Throwable cause) {
    super("Entity: " + className + ":" + entityName + " " + description, cause);
    this.className = className;
    this.entityName = entityName;
    this.description = description;
  }
}
