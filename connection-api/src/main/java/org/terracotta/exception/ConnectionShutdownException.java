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
 * <p>This specific {@link IllegalStateException} type is thrown, on the client-side, when a connection underneath an
 * issued lifecycle operation is closed.</p>
 */
public class ConnectionShutdownException extends IllegalStateException {

  /**
   * Creates a new instance with the given message.
   *
   * @param message The description of the exception.
   */
  public ConnectionShutdownException(String message) {
    super(message);
  }
}
