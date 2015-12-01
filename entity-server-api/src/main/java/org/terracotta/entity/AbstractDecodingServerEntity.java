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
 * A helper class for building simple server-side entities in a common pattern.
 * 
 * @param <M> The specific EntityMessage type of the invocation argument
 * @param <O> The specific invocation response type which must be encoded to send over the wire
 */
public abstract class AbstractDecodingServerEntity<M extends EntityMessage, O> implements ActiveServerEntity<M> {
  /**
   * Called with the result of invokeHighLevel() in order to encode that result into a byte[] for transmission over the wire.
   * 
   * @param o The output object
   * @return The encoded byte[]
   */
  protected abstract byte[] encodeOutput(O o);

  /**
   * Invokes with the message type, returning a high-level object which will be encoded for response, after the return.
   * 
   * @param clientDescriptor The sending of the invocation
   * @param input The message being invoked
   * @return A high-level output object
   */
  protected abstract O invokeHighLevel(ClientDescriptor clientDescriptor, M input);

  @Override
  public final byte[] invoke(ClientDescriptor clientDescriptor, M message) {
    return encodeOutput(invokeHighLevel(clientDescriptor, message));
  }
}
