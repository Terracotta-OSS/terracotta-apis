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
 * <p>This specific {@link RuntimeEntityException} type is thrown, on the client-side, when a connection underneath an
 * issued or in-flight call is closed.</p>
 * <p>It is unchecked since this is essentially a direct error in the client-side code:  closing a connection and then
 * trying to use it.  Under normal usage, this won't happen since connections are transparently reconnected, in the
 * background:  blocking instead of failing.</p>
 */
public class ConnectionClosedException extends RuntimeEntityException {
  private static final long serialVersionUID = 1L;
  private final boolean wasSent;
  
  public ConnectionClosedException(String description) {
    this(null, null, description, true, null);
  }

  public ConnectionClosedException(boolean wasSent, String description) {
    this(null, null, description, wasSent, null);
  }

  public ConnectionClosedException(boolean wasSent, String description, Throwable cause) {
    this(null, null, description, wasSent, cause);
  }
  
  public ConnectionClosedException(String description, Throwable cause) {
    this(null, null, description, true, cause);
  }  
  /**
   * Thrown went a connection was closed from under a message that has not yet returned an
   * answer from the cluster
   * @param type entity type
   * @param name entity name
   * @param description extra information about the exception
   * @param wasSent was the message sent to the remote cluster
   * @param cause the root cause of the exception
   */
  public ConnectionClosedException(String type, String name, String description, boolean wasSent, Throwable cause) {
    super(type, name, description, cause);
    this.wasSent = wasSent;
  }
  /**
   * Designates whether the message associated with this exception was sent to the cluster.
   * 
   * @return true of the message was sent not to the server
   */
  
  public boolean messageWasNotSent() {
    return !this.wasSent;
  }
}
