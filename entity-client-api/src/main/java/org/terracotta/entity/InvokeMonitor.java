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

import java.util.function.Consumer;
import org.terracotta.exception.EntityException;

/**
 * A response handler registered with the {@link InvocationBuilder} when multiple 
 * responses need to handled by a single invoke.
 * @param <R> the type of the response to be handled
 */
public interface InvokeMonitor<R extends EntityResponse> extends Consumer<R>, AutoCloseable {
  
  /**
   * Handle an exception in the chain of responses from the server.  NOTE: made default 
   * so Functional methods can be used when exception monitoring is not desired.
   * @param ee the exception that occured on the server
   */
  
  default void exception(EntityException ee) {
    
  }

  /**
   * Called by the platform when the invocation has been retired by the server.  Retirement
   * means that no more responses will be delivered to this monitor for this invocation 
   * and that the original message invocation will not be resent to the server on a 
   * reconnect.
   */
  @Override
  default void close() {
    
  }
  
}