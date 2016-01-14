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

import java.util.concurrent.Future;

import com.tc.classloader.CommonComponent;


/**
 * Communicator allowing server-side entities to push messages to client-side entities.
 *
 * @author twu
 */
@CommonComponent
public interface ClientCommunicator {

  /**
   * Send a message to the client-side of an entity.
   *
   * @param clientDescriptor The client side instance to send to
   * @param payload bytes to send
   */
  void sendNoResponse(ClientDescriptor clientDescriptor, byte[] payload);

  /**
   * Send a message getting an async completion back.
   *
   * NOTE: async completion back is extremely dangerous.  Any wait on the server
   * for client completion can have disastrous effects on the server.  DO NOT USE
   * THIS METHOD
   * 
   * @param clientDescriptor The client side instance to send to
   * @param payload bytes to send
   * @return Future representing when the client finishes with and acknowledges the sent message.
   */
  @Deprecated
  Future<Void> send(ClientDescriptor clientDescriptor, byte[] payload);
}
