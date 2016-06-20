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
 * Instances of this type are used to construct an invocation from the client to the server-side entity.  Each method
 * modifies the state of the instance and then returns itself so that several of these methods can be used in a chain.
 * Note that the invocation doesn't actually get sent to the server until invoke() is called.
 *
 * @param <M> An {@link EntityMessage}
 * @param <R> An {@link EntityResponse}
 */
public interface InvocationBuilder<M extends EntityMessage, R extends EntityResponse> {
  /**
   * Requests that the invocation wait on the SENT acknowledgement, before returning from invoke().
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackSent();

  /**
   * Requests that the invocation wait on the RECEIVED acknowledgement, before returning from invoke().
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackReceived();

  /**
   * Requests that the invocation wait on the COMPLETED acknowledgement, before returning from invoke().
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackCompleted();

  /**
   * Requests that the invocation wait on the RETIRED acknowledgement, before returning from invoke().
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackRetired();

  /**
   * Sets whether or not the invocation should be replicated to any passive servers in this stripe.
   * 
   * @param requiresReplication True if the message should be replicated, false otherwise
   * @return Itself
   */
  public InvocationBuilder<M, R> replicate(boolean requiresReplication);

  /**
   * Sets the message of the invocation.
   * 
   * @param message A high-level {@link EntityMessage}
   * @return Itself
   */
  public InvocationBuilder<M, R> message(M message);

  /**
   * By default, the get() blocks until the RETIRED ack is received (meaning that this message will NOT be re-sent).
   * In general, the RETIRED ack comes immediately after the COMPLETED ack.  Applications which rely on deferred
   * retirement, it is possible that there is a long time delay between these 2 acks, and it is possible to see many
   * COMPLETED acks before the RETIRED (this happens if the message is re-sent).
   * In some of these applications, it may give a client-side performance benefit to only wait for the first COMPLETED
   * ack, before returning from the get().  This method allows the client to change this behavior.
   * Note that, in the case of multiple COMPLETED acks, an invocation NOT blocking on RETIRED will return the result
   * which is associated with the first COMPLETED ack.  Additionally, even though the get() will return before the
   * RETIRED, the message may still be re-sent, in the background.
   * 
   * @param shouldBlock True if the get() should block on RETIRE (the default), false if it should only block on the
   * first COMPLETED ack.
   * @return Itself
   */
  public InvocationBuilder<M, R> blockGetOnRetire(boolean shouldBlock);

  /**
   * Actually sends the invocation staged in the receiver with encoded message {@link M} using {@link MessageCodec<M, R>} to the server.  Note that this will wait for any requested
   * acknowledgements before returning.
   *
   * Note that returned {@link InvokeFuture} will decode the response from server into {@link R} using {@link MessageCodec<M, R>}
   * 
   * @return The asynchronous result of the invocation
   * @throws MessageCodecException if there is an issue with encoding {@link M}/decoding {@link R}
   */
  public InvokeFuture<R> invoke() throws MessageCodecException;
}
