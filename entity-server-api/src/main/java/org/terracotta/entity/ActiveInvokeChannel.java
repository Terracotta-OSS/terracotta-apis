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

public interface ActiveInvokeChannel<R extends EntityResponse> extends AutoCloseable {
  /**
   * Send a response message to the client's InvokeMonitor.  If this channel has been closed, 
   * an IllegalStateException will be thrown
   * 
   * @param response delivered to InvokeMonitor on the client side
   */
  void sendResponse(R response);
  /**
   * Send an exception to the invoke channel.  Sending an exception will result in the 
   * immediate close of this channel
   * 
   * @param response exception delivered to InvokeMonitor on the client side
   */
  void sendException(Exception response);
  /**
   * closes the channel to the client side.  May also result in the retirement of 
   * the original message associated with this channel
   */
  @Override
  void close();
}
