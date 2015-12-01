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
 * The base class of the exception hierarchy used by the entity API.
 * On its own, doesn't convey the meaning of the exceptional circumstance.  To determine and handle these specific
 * situations, catch the more specific sub-class exceptions.
 */
public abstract class EntityException extends Exception {
  private static final long serialVersionUID = 1L;

  /**
   * All of these exception types have a specific description and refer to a type and name pair.
   * 
   * @param className The name of the type of the entity
   * @param entityName The name of the entity instance
   * @param description The description describing the specific problem
   */
  protected EntityException(String className, String entityName, String description) {
    super("Entity: " + className + ":" + entityName + " " + description);
  }
}
