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
package org.terracotta.entity;

/**
 * An opaque token representing a specific entity instance on a specific client node.
 * This is used by server-side code to specifically communicate with or track connection
 * status of a specific client-side object instance. This is used both in actives
 * and passives in certain cases.
 * Note that implementations are expected to provide a proper equals()/hashCode().
 */
public interface ClientDescriptor {

  /**
   * Client source id for this client descriptor.
   * @return
   */
  ClientSourceId getSourceId();
  /**
   *
   * @return true if the @see ClientSourceId associated with this descriptor is a valid
   * remote client and not associated with a server call
   */
  default boolean isValidClient() {
    return getSourceId().isValidClient();
  }
}
