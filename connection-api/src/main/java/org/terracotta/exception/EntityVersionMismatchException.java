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
 * This specific EntityException type is thrown in cases where an entity couldn't be created or fetched because the client
 * tried to access it using an incompatible entity version.
 * In the case of creation, this does still imply that an entity with that class and name otherwise could be created.
 * In the case of a fetch, this does imply that an entity with that class and name does exist.
 */
public class EntityVersionMismatchException extends EntityException {
  private static final long serialVersionUID = 1L;
  private final long expectedVersion;
  private final long attemptedVersion;

  /**
   * Creates the exception instance describing the given type-name pair along with a description of the version mismatch.
   * 
   * @param className The name of the entity type
   * @param entityName The name of the entity instance
   * @param expectedVersion The version of the actual entity instance
   * @param attemptedVersion The version the client tried to use, which was incorrect
   */
  public EntityVersionMismatchException(String className, String entityName, long expectedVersion, long attemptedVersion) {
    super(className, entityName, "version mismatch (expected " + expectedVersion + " but attempted " + attemptedVersion + ")", null);
    this.expectedVersion = expectedVersion;
    this.attemptedVersion = attemptedVersion;
  }

  /**
   * Creates the exception instance describing the given type-name pair along with a description of the version
   * mismatch and underlying cause of this exception
   *
   * @param className The name of the entity type
   * @param entityName The name of the entity instance
   * @param expectedVersion The version of the actual entity instance
   * @param attemptedVersion The version the client tried to use, which was incorrect
   * @param cause underlying cause of this exception
   */
  public EntityVersionMismatchException(String className, String entityName, long expectedVersion, long attemptedVersion, Throwable cause) {
    super(className, entityName, "version mismatch (expected " + expectedVersion + " but attempted " + attemptedVersion + ")", cause);
    this.expectedVersion = expectedVersion;
    this.attemptedVersion = attemptedVersion;
  }

  public long getExpectedVersion() {
    return expectedVersion;
  }

  public long getAttemptedVersion() {
    return attemptedVersion;
  }
}
