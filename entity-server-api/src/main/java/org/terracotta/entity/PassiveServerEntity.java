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
 * The methods specifically supported by passive entities.  Note that a passive doesn't know anything about
 *  connected clients, nor can it return data to them.
 * Note that passive entities have no invoke response but we still accept the generic R so that accessors of the codec will
 * see a consistent type landscape.
 */
public interface PassiveServerEntity<M extends EntityMessage, R extends EntityResponse> extends CommonServerEntity<M, R> {
  /**
   * Invoke a call on the given entity.  Note that passive entities can't return data to the client.
   * This method is called both in the cases of normal client->server method invocation but also in the case of
   * server->server passive synchronization.
   *
   * @param message The message from a client or upstream active server
   */
  void invoke(M message);

  /**
   * Called on MANAGEMENT_KEY to notify the receiver that it is about to start receiving synchronization messages.
   */
  void startSyncEntity();

  /**
   * Called on MANAGEMENT_KEY to notify the receiver synchronization is complete and it will see no more synchronization
   * messages.
   */
  void endSyncEntity();

  /**
   * Called on concurrencyKey to notify the receiver that it is about to start receiving synchronization messages on this
   * key.
   */
  void startSyncConcurrencyKey(int concurrencyKey);

  /**
   * Called on concurrencyKey to notify the receiver that synchronization of this key is complete and no more
   * synchronization messages will be received on that key.
   */
  void endSyncConcurrencyKey(int concurrencyKey);
}
