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
 *  The Covered Software is Entity API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */

package org.terracotta.entity;

/**
 * The message deserializer used to convert incoming byte[] data into high-level messages to be passed to the entity for
 * invocation.
 * Note that the implementation cannot assume when it will be called, relative to any other calls related to the entity, or
 * even that the calls will be made sequentially.  Multiple messages should be concurrently deserializable.
 * This means that an implementation is expected to be state-less and each returned EntityMessage must not be cached or
 * reused.
 * @param <M> The message type deserialized by the implementation.
 */
public interface MessageDeserializer<M extends EntityMessage> {
  /**
   * Deserializes a given message payload into a high-level message type, useful to the server-side entity.
   * 
   * @param payload The byte array containing the raw wire message
   * @return A high-level message instance
   */
  M deserialize(byte[] payload);
}
