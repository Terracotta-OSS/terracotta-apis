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
