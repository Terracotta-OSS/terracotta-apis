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
 * The methods specifically supported by active entities.  As the active is responsible for "driving" much of the
 *  interaction, it has more capabilities than the corresponding passive.
 */
public interface ActiveServerEntity<M extends EntityMessage> extends CommonServerEntity<M> {
  /**
   * Get the concurrency strategy to be used for this server entity.
   *
   * @return concurrency strategy
   */
  ConcurrencyStrategy<M> getConcurrencyStrategy();

  /**
   * Indicate that the given client is now connected up to this ServerEntity.
   *
   * @param clientDescriptor client-side instance which connected
   */
  void connected(ClientDescriptor clientDescriptor);

  /**
   * Notify the ServerEntity that the given client has disconnected.
   *
   * @param clientDescriptor client-side instance which disconnected
   */
  void disconnected(ClientDescriptor clientDescriptor);

  /**
   * Get configuration for given entity
   *
   * @return serialized byte array
   */
  byte[] getConfig();
  
  /**
   * Invoke a call on the given entity.
   *
   * @param clientDescriptor source instance from which the invocation originates.
   * @param message The message from the client
   * @return possible return value
   */
  byte[] invoke(ClientDescriptor clientDescriptor, M message);

  /**
   * Called during client reconnect to allow the client to pass arbitrary extra data to the server-side entity so it can
   * rebuild any in-memory state it had, related to the connected client.
   * Note that this is called AFTER connected() is called for this clientDescriptor.
   * 
   * @param clientDescriptor The client-side instance which reconnected
   * @param extendedReconnectData Arbitrary data sent by the client-side instance to rebuild the server-side in-memory state 
   */
  void handleReconnect(ClientDescriptor clientDescriptor, byte[] extendedReconnectData);
}
