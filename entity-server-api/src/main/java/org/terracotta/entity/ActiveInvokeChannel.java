/*
 * Copyright Terracotta, Inc.
 * Copyright IBM Corp. 2024, 2025
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
