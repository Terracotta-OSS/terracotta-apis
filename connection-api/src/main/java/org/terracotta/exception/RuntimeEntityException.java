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
package org.terracotta.exception;


/**
 * The base class of the runtime exception hierarchy used by the entity API.
 * On its own, doesn't convey the meaning of the exceptional circumstance.  To determine and handle these specific
 * situations, catch the more specific sub-class exceptions.
 */
public abstract class RuntimeEntityException extends RuntimeException {
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
  protected RuntimeEntityException(String className, String entityName, String description, Throwable cause) {
    super("Entity: " + className + ":" + entityName + " " + description, cause);
    if (className == null) {
      throw new NullPointerException("Entity type");
    }
    if (entityName == null) {
      throw new NullPointerException("Entity name");
    }
    this.className = className;
    this.entityName = entityName;
    this.description = description;
  }

  protected RuntimeEntityException(String className, String entityName, String description) {
    super("Entity: " + className + ":" + entityName + " " + description);
    if (className == null) {
      throw new NullPointerException("Entity type");
    }
    if (entityName == null) {
      throw new NullPointerException("Entity name");
    }
    this.className = className;
    this.entityName = entityName;
    this.description = description;
  }
}
