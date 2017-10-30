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
   * @param message The message to send
   * @throws MessageCodecException If the message could not be serialized by the provided codec
   */
  void sendNoResponse(ClientDescriptor clientDescriptor, EntityResponse message) throws MessageCodecException;
  /**
   * Unilaterally closes the server-side connection associated with entity ClientDescriptor.
   * Any other entity ClientDescriptors associated with the connection will also be invalidated
   * There is no notification as to whether this method has succeeded in its goal as all interactions
   * are asynchronous.  For confirmation of connection closer, consult entity based callbacks.
   * 
   * @param clientDescriptor The client side instance the connection to be closed is associated with
   */
  void closeClientConnection(ClientDescriptor clientDescriptor);
}
