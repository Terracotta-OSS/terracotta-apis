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
 * <p>This specific {@link RuntimeEntityException} type is thrown, on the client-side, when a connection underneath an
 * issued or in-flight call is closed.</p>
 * <p>It is unchecked since this is essentially a direct error in the client-side code:  closing a connection and then
 * trying to use it.  Under normal usage, this won't happen since connections are transparently reconnected, in the
 * background:  blocking instead of failing.</p>
 */
public class ConnectionClosedException extends RuntimeEntityException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new instance with the given description.
   * 
   * @param description The description of the exception.
   */
  public ConnectionClosedException(String description) {
    super(null, null, description, null);
  }

  /**
   * Creates a new instance with the given description and underlying cause.
   * 
   * @param description The description of the exception.
   * @param cause The underlying cause of the exception.
   */
  public ConnectionClosedException(String description, Throwable cause) {
    super(null, null, description, cause);
  }
}
