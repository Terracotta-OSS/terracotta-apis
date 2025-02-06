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
package org.terracotta.entity;

/**
 * Represents a client, which may have multiple ClientDescriptors being used through it.
 * Implementations are required to implement equals()/hashcode() properly, and equality
 * of the opaque long must be equivalent to the equality of the instance objects.
 */
public interface ClientSourceId {
  /**
   * invalid clients are negative numbers
   */

  long NULL_ID = -1L;
  /**
   * Opaque long representation of this source.
   * @return long
   */
  long toLong();

  /**
   *
   * @return true if this is a valid client reference and not associated with
   * a server initiated request
   */
  default boolean isValidClient() {
    return toLong() > NULL_ID;
  }

  /**
   * Whether a specific ClientDescriptor is tied to this ClientSource
   * @param cd
   * @return true if this descriptor belongs to this client source
   */
  boolean matches(ClientDescriptor cd);
}
