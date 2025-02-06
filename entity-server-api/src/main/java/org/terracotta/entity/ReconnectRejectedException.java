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
 * When an entity is handling reconnect data from a client, if this exception is thrown, 
 * the reconnection of that client will be rejected, and the Connection associated with the
 * entity will be disconnected from the server.
 */
public class ReconnectRejectedException extends Exception {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new instance of <code>ReconnectRejectedException</code>
   * 
   * @param description an implementation defined description of the problem
   */
  public ReconnectRejectedException(String description) {
    this(description, null);
  }

  /**
   * Creates a new instance of <code>ReconnectRejectedException</code> along with underlying cause.
   *
   * @param description an implementation defined description of the problem
   * @param cause underlying cause of this exception
   */
  public ReconnectRejectedException(String description, Throwable cause) {
    super(description, cause);
  }
}
