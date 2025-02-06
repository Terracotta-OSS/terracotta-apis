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
 * <p>This specific {@link RuntimeEntityException} type is thrown in cases where an entity couldn't be destroyed since
 * it is a permanent entity, defined within the server's configuration.</p>
 * <p>It is unchecked since this is essentially a static programming or configuration error and is not possible in
 * already-tested code and configurations.</p>
 */
public class PermanentEntityException extends RuntimeEntityException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new instance of <code>PermanentEntityException</code> without
   * detail message.  This exception is thrown when a client attempts to delete a
   * permanent entity created on the server.
   * 
   * @param className class of the entity that was being deleted
   * @param entityName name of the entity being deleted
   */
  public PermanentEntityException(String className, String entityName) {
    super(className, entityName, "permanent entity", null);
  }

  /**
   * Creates a new instance of <code>PermanentEntityException</code> along with underlying cause but without
   * detail message.  This exception is thrown when a client attempts to delete a
   * permanent entity created on the server.
   *
   * @param className class of the entity that was being deleted
   * @param entityName name of the entity being deleted
   * @param cause underlying cause of this exception
   */
  public PermanentEntityException(String className, String entityName, Throwable cause) {
    super(className, entityName, "permanent entity", cause);
  }
}
