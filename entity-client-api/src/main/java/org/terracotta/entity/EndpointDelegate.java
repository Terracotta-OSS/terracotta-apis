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
 * The interface associated with an EntityClientEndpoint to handle the events which enter the system at that object.
 * Typically, the implementor is the Entity associated with the end-point (hence Entity extends this interface).
 */
public interface EndpointDelegate {
  /**
   * Handle a message coming from the clustered implementation of the Entity.
   * Note that the message comes back as an EntityResponse object, maintaining consistency with the normal message flow:
   * -client->server (EntityMessage)
   * -server->client (EntityResponse)
   *
   * @param messageFromServer The EntityResponse object sent by the server
   */
  public void handleMessage(EntityResponse messageFromServer);

  /**
   * Called on the thread handling the reconnect to ask if any additional data should be provided to the server-side entity,
   * upon reconnect from this entity.  Note that the value returned here will be provided to the server-side entity via
   * "handleReconnect()"
   * @return Arbitrary data to send the server-side entity when handling the reconnect of this end-point
   */
  public byte[] createExtendedReconnectData();

  /**
   * Called when the end-point unexpectedly (and unrecoverably) disconnected from the remote side.
   * Called on the thread which realized the disconnect.
   */
  public void didDisconnectUnexpectedly();
}
