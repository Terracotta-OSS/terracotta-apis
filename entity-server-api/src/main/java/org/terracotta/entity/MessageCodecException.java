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
 * An exception provided for errors encountered in a MessageCodec implementation.
 * These exceptions can be thrown from the implementation of the MessageCodec or thrown by the platform implementation when
 * a RuntimeException is thrown from a MessageCodec method.
 * Note that these exceptions will typically be wrapped in an EntityUserException when thrown back to the client.
 */
public class MessageCodecException extends Exception {
  private static final long serialVersionUID = 1L;

  public MessageCodecException(String description, Throwable t) {
    super(description, t);
  }
}
