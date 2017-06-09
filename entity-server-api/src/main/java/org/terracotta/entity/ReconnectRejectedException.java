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
