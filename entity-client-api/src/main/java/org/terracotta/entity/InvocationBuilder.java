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

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * <p>Instances of this type are used to construct an invocation from the client to the server-side entity.  Each method
 * modifies the state of the instance and then returns itself so that several of these methods can be used in a chain.
 * Note that the invocation doesn't actually get sent to the server until {@link #invoke()} is called.</p>
 * 
 * <p>The acknowledgement configurations can be complex so a high-level description of them and their meanings is
 * provided here, with greater detail in the relevant methods, below:</p>
 * <ul>
 *  <li><b>SENT</b> means that a message has become "in-flight", meaning that it will be re-sent in the event of a
 *  server restart or fail-over to passive. Note that this message is not generally useful to applications</li>
 *  <li><b>RECEIVED</b> if requested, will only return from invoke() once the global order of this message (relative to
 *  others depending on RECEIVED) has been determined for the entire stripe. This means that every server in the stripe
 *  has received the message, and agreed upon the order. Note that RECEIVED is the only optional ack (the others always
 *  go over the wire, but only optionally influence client-side waiting). This should be requested in cases where
 *  knowing the global order of messages (preserved for restart or fail-over) is important</li>
 *  <li><b>COMPLETED</b> is observed by the client when the active has finished running the invoke and returned the
 *  result. Calling get() on the returned InvokeFuture will, by default, wait until RETIRED but the InvocationBuilder
 *  can be configured to make the get() return only once COMPLETED has been received. Note that this only relates to
 *  what has happened on the active: it is possible for the client to see the COMPLETED ack before any passives have
 *  seen the message</li>
 *  <li><b>RETIRED</b> is the final acknowledgement, after which time the message will not be re-sent in the event of
 *  restart or fail-over. It represents the point where the message has completed on all servers in the stripe and also
 *  any messages it created to defer its retirement (see {@link IEntityMessenger}) have also completed on all servers in
 *  the stripe</li>
 * </ul>
 * 
 * <p>Requested acks generally only apply to when the {@link #invoke()} method will return but some messages explicitly
 * change the behavior of the returned {@link InvokeFuture#get()} (to either wait on COMPLETED or RETIRED).</p>
 * 
 * @param <M> An {@link EntityMessage}
 * @param <R> An {@link EntityResponse}
 */
public interface InvocationBuilder<M extends EntityMessage, R extends EntityResponse> {
  /**
   * <p>Requests that the invocation wait on the SENT acknowledgement, before returning from {@link #invoke()}.</p>
   * 
   * <p>Once a message has been SENT, but not yet RETIRED, it will be re-sent in the event of a restart or
   * fail-over.</p>
   * 
   * <p>This method is not of use to most applications.</p>
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackSent();

  /**
   * <p>Requests that the invocation wait on the RECEIVED acknowledgement, before returning from {@link #invoke()}.</p>
   * 
   * <p>The RECEIVED ack is used to determine global message order.  Once the RECEIVED ack has been received, it implies
   * that a re-send of the message, for either a restart or fail-over, will be run in the same order as determined
   * before the re-send.</p>
   * 
   * <p>This method is important for applications which need more direct visibility into global message ordering.</p>
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackReceived();

  /**
   * <p>Requests that the invocation wait on the COMPLETED acknowledgement, before returning from {@link #invoke()}.</p>
   * 
   * <p>Note that, unless also requested with the RECEIVED ack, this will only ensure that the message has completed on
   * the active, and may not yet have been run on (or seen by) any passives.</p>
   * 
   * <p>This method is not of use to most applications since configuring and using {@link InvokeFuture#get()} is
   * generally more useful and descriptive.</p>
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackCompleted();

  /**
   * <p>Requests that the invocation wait on the RETIRED acknowledgement, before returning from {@link #invoke()}.</p>
   * 
   * <p>Once a message has been RETIRED, it will <b>not</b> be re-sent in the event of a restart or fail-over.</p>
   * 
   * <p>This method is not of use to most applications since configuring and using {@link InvokeFuture#get()} is
   * generally more useful and descriptive (by default, it blocks until RETIRED).</p>
   * 
   * @return Itself
   */
  public InvocationBuilder<M, R> ackRetired();

  /**
   * <p>Sets whether or not the invocation should be replicated to any passive servers in this stripe.</p>
   * 
   * <p>If this method call is omitted from builder, the default behavior is to replicate the message.</p>
   * 
   * <p>Note that it is typically more useful to use {@link ExecutionStrategy} since it gives more complete control over
   * message flow.</p>
   * 
   * @param requiresReplication True if the message should be replicated, false otherwise
   * @return Itself
   */
  public InvocationBuilder<M, R> replicate(boolean requiresReplication);

  /**
   * <p>Sets the message of the invocation.</p>
   * 
   * @param message A high-level {@link EntityMessage}
   * @return Itself
   */
  public InvocationBuilder<M, R> message(M message);
  /**
   * Set the response handler for the invocation.  Multiple responses to an invocation 
   * can be sent by the server to the client via {@link org.terracotta.entity.ActiveInvokeChannel#sendResponse(EntityResponse)}.
   * This handler is used to process those extra responses
   * @param consumer a Response handler for the invocation
   * @return Itself
   */
  public InvocationBuilder<M, R> monitor(InvokeMonitor<R> consumer);
  /**
   * An executor used to deliver messages to the InvokeMonitor.  The default delivery thread
   * is a the platform thread used to report all message responses to all invocations.  As a 
   * result, no blocking operations or message related operations can occur directly in the 
   * handler or deadlock can occur.  Use the executor to specify the threading behavior for 
   * callback delivery.
   * @param useForMonitorDelivery Executor used for InvokeMonitor response delivery
   * @return 
   */
  public InvocationBuilder<M, R> withExecutor(Executor useForMonitorDelivery);
  /**
   * Changes the response order of the invoke.  Normally, {@link InvokeFuture#get()} 
   * will return only the result of the original message sent to the server as a result of 
   * {@link org.terracotta.entity.ActiveServerEntity#invokeActive(ActiveInvokeContext, EntityMessage)}
   * if this option is selected, the value of {@link InvokeFuture#get()} is completely 
   * time order based.  The value returned by {@link InvokeFuture#get()} will be the last
   * {@link EntityResponse} delivered to the invocation before the retire regardless of whether 
   * the {@link EntityResponse} was the result of a return from {@link org.terracotta.entity.ActiveServerEntity#invokeActive(ActiveInvokeContext, EntityMessage)}
   * or a {@link org.terracotta.entity.ActiveInvokeChannel#sendResponse(EntityResponse)} call.
   * All other responses will be delivered to the {@link InvokeMonitor} if registered.
   * @return 
   */
  public InvocationBuilder<M, R> asDeferredResponse();
  /**
   * <p>By default, the {@link get()} blocks until the RETIRED ack is received (meaning that this message will NOT be
   * re-sent).</p>
   * 
   * <p>The RETIRED ack comes after the COMPLETED ack but can come much later in 2 situations:</p>
   * <ol>
   *  <li>Any passive servers are substantially lagging behind the active.  This is because the COMPLETED arrives
   *  immediately after the active has finished the invoke of the message, whereas RETIRED comes in after all servers
   *  have finished the invoke.</li>
   *  <li>The message deferred its retirement to a message it created (see {@link IEntityMessenger}).  In those cases,
   *  the RETIRED only arrives once both the initial message and any of these messages it created have completed on all
   *  servers.</li>
   * </ol>
   * 
   * <p>Note that, due to re-sends, it is possible to see the COMPLETED ack, multiple times, before seeing the final
   * RETIRED (which only happens once).</p>
   * 
   * <p>If {@link get()} is blocking until RETIRED, it will return the result included in the <b>final</b> COMPLETED
   * ack.  If it is only blocking until COMPLETED, it will return the result included in the <b>first</b> COMPLETED ack
   * (ignoring the results from any later COMPLETED acks which arrive).</p>
   * 
   * <p>In some applications, it may give a client-side performance benefit to only wait for the first COMPLETED
   * ack, before returning from the get().  This method allows the client to change this behavior.</p>
   * 
   * <p>Note that, even if only waiting for COMPLETED, the message may still continue to be re-sent, in the background,
   * in response to restart of fail-over events.</p>
   * 
   * @param shouldBlock True if the get() should block on RETIRE (the default), false if it should only block on the
   * first COMPLETED ack.
   * @return Itself
   */
  public InvocationBuilder<M, R> blockGetOnRetire(boolean shouldBlock);

  /**
   * <p>Actually sends the invocation staged in the receiver with encoded message {@link M} using
   * {@link MessageCodec<M, R>} to the server.  Note that this will wait for any requested acknowledgements before
   * returning.  The blocking behavior of the returned {@link InvokeFuture} will depend on how it was configured via
   * {@link #blockGetOnRetire(boolean)}.</p>
   *
   * <p>Note that returned {@link InvokeFuture} will decode the response from server into {@link R} using
   * {@link MessageCodec<M, R>}</p>
   * 
   * @return The asynchronous result of the invocation
   * @throws MessageCodecException if there is an issue with encoding {@link M}/decoding {@link R}
   */
  public InvokeFuture<R> invoke() throws MessageCodecException;

  /**
   * <p>Same as invoke with added timeout parameters.  This timeout is used to specify a time limit
   * for the client to send the message to the server which is separate from {@link InvokeFuture#getWithTimeout} 
   * which specifies the time limit for the response to received from the server</p>
   * 
   * @param time
   * @param units
   * @return The asynchronous result of the invocation
   * @throws java.lang.InterruptedException
   * @throws java.util.concurrent.TimeoutException
   * @throws MessageCodecException if there is an issue with encoding {@link M}/decoding {@link R}
   */
  public InvokeFuture<R> invokeWithTimeout(long time, TimeUnit units) throws InterruptedException, TimeoutException, MessageCodecException;
}
