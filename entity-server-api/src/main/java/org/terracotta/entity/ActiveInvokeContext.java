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

import java.util.Properties;

public interface ActiveInvokeContext<R extends EntityResponse> extends InvokeContext {
  /**
   * source instance from which the invocation originates.
   *
   * @return descriptor
   */
  ClientDescriptor getClientDescriptor();
  /**
   * Opens a channel to consumer of this context.If the context is associated with a 
   *  client side invoke, this will open a channel to the InvokeMonitor on the client.  If this context is associated with a server invoke, a channel is opened to the 
   *  consumer of the message.  The channel MUST be closed or an exception set or the 
   *  message will never be retired from the system and result in a resource leak.
   *
   * @return a channel to send messages to the originator of the message associated with this context
   */
  ActiveInvokeChannel<R> openInvokeChannel();
  /**
   * Returns a map of client source information provided by the implementation.  Examples
   * might be the remote IP address of the source or the type of connection used to issue 
   * the command.
   * @return a properties map with implementation provided information
   */
  default Properties getClientSourceProperties() {
      return new Properties();
  }

}
