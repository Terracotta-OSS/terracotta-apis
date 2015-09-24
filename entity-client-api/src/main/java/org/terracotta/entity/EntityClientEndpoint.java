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

import java.io.Closeable;


public interface EntityClientEndpoint extends Closeable {

  byte[] getEntityConfiguration();

  /**
   * Sets the delegate for events originating within the end-point.
   * Note that this value can only be set once.
   * @param delegate The delegate to use for events originating within the receiver.
   */
  void setDelegate(EndpointDelegate delegate);

  InvocationBuilder beginInvoke();

  /**
   * Called when constructing the reconnect handshake, when the connection under this endpoint is re-established after
   * restart or fail-over in order to ask if the entity on top of the endpoint has registered any special data to be sent
   * to the server-side entity.
   * @return A non-null array of bytes to send.
   */
  byte[] getExtendedReconnectData();

  /**
   * The instance will be unusable after this call.
   */
  @Override
  void close();

  /**
   * Called if the receiver was closed unexpectedly in a way which can't be reconnected.  This can be due to a failure to
   * reconnect within the window, the restart of a non-persistent server, or client shutdown without previously having
   * called close().
   * The receiver is considered to be invalid, once this call is received.
   */
  void didCloseUnexpectedly();
}
