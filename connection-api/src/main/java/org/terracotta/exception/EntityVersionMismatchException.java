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
 * This specific EntityException type is thrown in cases where an entity couldn't be created or fetched because the client
 * tried to access it using an incompatible entity version.
 * In the case of creation, this does still imply that an entity with that class and name otherwise could be created.
 * In the case of a fetch, this does imply that an entity with that class and name does exist.
 */
public class EntityVersionMismatchException extends EntityException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates the exception instance describing the given type-name pair along with a description of the version mismatch.
   * 
   * @param className The name of the entity type
   * @param entityName The name of the entity instance
   * @param expectedVersion The version of the actual entity instance
   * @param attemptedVersion The version the client tried to use, which was incorrect
   */
  public EntityVersionMismatchException(String className, String entityName, long expectedVersion, long attemptedVersion) {
    super(className, entityName, "version mismatch (expected " + expectedVersion + " but attempted " + attemptedVersion + ")");
  }
}
