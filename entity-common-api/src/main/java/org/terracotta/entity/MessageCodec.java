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
 *
 * A MessageCodec is for converting high-level EntityMessages/EntityResponses to byte[] and vice-versa
 *
 * Note that the implementation cannot assume when it will be called, relative to any other calls related to the entity, or
 * even that the calls will be made sequentially.  Multiple messages should be concurrently serialized or deserialized.
 * Also, encode/decode may not be called on the same object and invocations could be across jvms (for example, client and
 * server jvms on different machines). This means that an implementation is expected to be state-less and each returned
 * {@link EntityMessage}/{@link EntityResponse} or byte[] must not be cached or reused.
 *
 *
 * @param <M> An {@link EntityMessage} to be encoded/decoded
 * @param <R> An {@link EntityResponse} to be encoded/decoded
 */
public interface MessageCodec<M extends EntityMessage, R extends EntityResponse> {

  /**
   * Encodes the given {@link EntityMessage} object into a byte[] which can be passed over the wire
   *
   * @param message The request object to be encoded
   * @return The encoded data which can be passed over the wire
   * @throws MessageCodecException The message could not be serialized or was considered invalid.
   */
  byte[] encodeMessage(M message) throws MessageCodecException;

  /**
   * Decodes a given payload into a high-level {@link EntityMessage} instance
   * 
   * @param payload The byte array containing the raw wire message
   * @return A high-level message instance
   * @throws MessageCodecException The message could not be deserialized or was considered invalid.
   */
  M decodeMessage(byte[] payload) throws MessageCodecException;

  /**
   * Encodes a given {@link EntityResponse} object into a byte[] which can be passed over the wire
   *
   * @param response The response object to be encoded
   * @return The raw data which can be passed over the wire
   * @throws MessageCodecException The message could not be serialized or was considered invalid.
   */
  byte[] encodeResponse(R response) throws MessageCodecException;

  /**
   * Decodes a given payload into a high-level {@link EntityResponse} instance
   *
   * @param payload The byte array containing the raw wire message
   * @return A high-level message instance
   * @throws MessageCodecException The message could not be deserialized or was considered invalid.
   */
  R decodeResponse(byte[] payload) throws MessageCodecException;


}
