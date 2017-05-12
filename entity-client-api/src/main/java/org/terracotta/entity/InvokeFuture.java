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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.terracotta.exception.EntityException;


/**
 * <p>Used to return the results of invocations to the server, asynchronously.  This allows the client to block on the
 * result at a later time.</p>
 * 
 * @param <T> The underlying type wrapped by the instance
 */
public interface InvokeFuture<T> {
  /**
   * @return True if the invocation has returned, false if it is still waiting
   */
  public boolean isDone();

  /**
   * <p>Returns the underlying result or throws the underlying exception, if satisfied.  Otherwise, blocks until it has
   * satisfied its ack requirements or an interrupt.</p>
   * 
   * <p>Note that these ack requirements are configured via
   * {@link org.terracotta.entity.InvocationBuilder#blockGetOnRetire(boolean)}, when the instance was created.  By
   * default, this method waits until RETIRED but that method can be used to configure it to return after the first
   * COMPLETED.</p>
   * 
   * @return The underlying result of the invocation, if successful
   * @throws InterruptedException If the call was interrupted while blocked
   * @throws EntityException The underlying error in the invocation, if unsuccessful
   */
  public T get() throws InterruptedException, EntityException, EntityUserException;

  /**
   * <p>Returns the underlying result or throws the underlying exception, if satisfied.  Otherwise, blocks until it has
   * satisfied its ack requirements, an interrupt, or a timeout.</p>
   * 
   * <p>Details of ack requirements described in {@link #get()}.</p>
   * 
   * @param timeout The number of TimeUnits to wait until throwing TimeoutException, if no response
   * @param unit The TimeUnit to use in determining how long to wait before throwing TimeoutException
   * @return The underlying result of the invocation, if successful
   * @throws InterruptedException If the call was interrupted while blocked
   * @throws EntityException The underlying error in the invocation, if unsuccessful
   * @throws TimeoutException The get timed out before a response was received
   */
  public T getWithTimeout(long timeout, TimeUnit unit) throws InterruptedException, EntityException, EntityUserException, TimeoutException;

  /**
   *<p> Interrupts the underlying {@link #get()} or {@link #getWithTimeout(long, TimeUnit)}, if blocked.  Will result in
   *the blocked thread throwing {@link InterruptedException}.</p>
   */
  public void interrupt();
}
