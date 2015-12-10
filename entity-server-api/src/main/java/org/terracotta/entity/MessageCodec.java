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
 * This interface is used, on the server-side, to convert incoming byte[] data into high-level EntityMessage objects and then
 * convert outgoing EntityResponse objects into byte[] data to send back over the wire.  This is used both for the normal
 * "invoke" path as well as passive synchronization.
 * Note that the implementation cannot assume when it will be called, relative to any other calls related to the entity, or
 * even that the calls will be made sequentially.  Multiple messages should be concurrently serialized or deserialized.
 * This means that an implementation is expected to be state-less and each returned EntityMessage or byte[] must not be
 * cached or reused.
 * @param <M> The message type deserialized from byte[] by the implementation.
 * @param <R> The message type serialized to byte[] by the implementation.
 */
public interface MessageCodec<M extends EntityMessage, R extends EntityResponse> {
  /**
   * Deserializes a given message payload into a high-level message type, useful to the server-side entity.
   * This deserializer routine is used for normal client->server message invocations.
   * 
   * @param payload The byte array containing the raw wire message
   * @return A high-level message instance
   */
  M deserialize(byte[] payload);

  /**
   * Deserializes a given message payload into a high-level message type, useful to the server-side entity.
   * This deserializer routine is used specifically for server->server passive synchronization.
   * 
   * @param concurrencyKey The concurrency key where the message must be run (so it can be described in the message type)
   * @param payload The byte array containing the raw wire message
   * @return A high-level message instance
   */
  M deserializeForSync(int concurrencyKey, byte[] payload);

  /**
   * Serializes the given EntityResponse object into a byte[] which can be passed over the wire, back to a client.
   * Note that the response argument may be null (if that is what the entity invoke() returned) but this method
   * must NOT return null.
   * 
   * @param response The response object received as the return value from a call to invoke 
   * @return The raw data which can be passed over the wire to the client
   */
  byte[] serialize(R response);
}
