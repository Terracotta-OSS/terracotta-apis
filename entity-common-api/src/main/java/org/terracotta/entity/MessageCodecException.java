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
