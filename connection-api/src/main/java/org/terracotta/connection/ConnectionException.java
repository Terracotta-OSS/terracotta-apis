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
package org.terracotta.connection;


/**
 * The exception type thrown when a connection fails to be established by the connection service.
 */
public class ConnectionException extends Exception {
  private static final long serialVersionUID = 1L;

  /**
   * This type can only be created as a high-level wrapper over the underlying cause.
   * 
   * @param cause The underlying throwable, describing what went wrong, specifically
   */
  public ConnectionException(Throwable cause) {
    super(cause);
  }
}
