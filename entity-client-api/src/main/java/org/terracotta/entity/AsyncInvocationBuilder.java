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


import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>Instances of this type are used to construct an invocation from the client to the server-side entity.  Each method
 * modifies the state of the instance and then returns itself so that several of these methods can be used in a chain.
 * Note that the invocation doesn't actually get sent to the server until {@link #invoke(InvocationCallback)} is
 * called.</p>
 *
 * @param <M> An {@link EntityMessage}
 * @param <R> An {@link EntityResponse}
 */
public interface AsyncInvocationBuilder<M extends EntityMessage, R extends EntityResponse> {

  /**
   * <p>Sets whether or not the invocation should be replicated to any passive servers in this stripe.</p>
   *
   * <p>If this method call is omitted from builder, the default behavior is to replicate the message.</p>
   *
   * @param requiresReplication True if the message should be replicated, false otherwise
   * @return Itself
   */
  AsyncInvocationBuilder<M, R> replicate(boolean requiresReplication);

  /**
   * <p>Sets the message of the invocation.</p>
   *
   * @param message A high-level {@link EntityMessage}
   * @return Itself
   */
  AsyncInvocationBuilder<M, R> message(M message);

  /**
   * <p>Block the {@link #invoke(InvocationCallback)} call until the message was enqueued or until timeout.</p>
   * @param time the timeout value.
   * @param unit the timeout unit.
   * @return Itself
   */
  AsyncInvocationBuilder<M, R> blockEnqueuing(long time, TimeUnit unit);

  /**
   * <p>Actually sends the invocation staged in the receiver with encoded message {@link M} using
   * {@link MessageCodec<M, R>} to the server. The <code>callback</code> will get called along the way each time a
   * certain acknowledgement is reached.
   *
   * @param callback the callback on which to notify the acknowledgements.
   * @throws RejectedExecutionException if the send queue is full immediately when this method was called, or after
   * the configured timeout elapsed. In the latter case, the <code>cause</code> of the
   * {@link RejectedExecutionException} is a {@link java.util.concurrent.TimeoutException}.
   */
  void invoke(InvocationCallback<R> callback) throws RejectedExecutionException;
}
