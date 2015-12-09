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
 * The methods common to both active and passive entities.
 * @param <M> The high-level message type used by the active and passive server-side entities.  This type is created by the
 * implementation's MessageCodec and also read by the ConcurrencyStrategy.
 * @param <R> The high-level message type returned by invoke calls on the active entity.
 */
public interface CommonServerEntity<M extends EntityMessage, R extends EntityResponse> {
  /**
   * Gets the message codec which will be used to convert any byte[] messages destined for this entity into
   * higher-level objects and conversely convert any response objects into byte[] for the wire.
   *
   * @return message codec
   */
  MessageCodec<M, R> getMessageCodec();
  
  /**
   * Called when a client asks that an entity be explicitly created.
   */
  void createNew();
  
  /**
   * Called when starting a server from a persistent state and the entity is expected to already be known to the server.
   */
  void loadExisting();
  
  /**
   * Destroy all state associated with this entity.
   */
  void destroy();
}
