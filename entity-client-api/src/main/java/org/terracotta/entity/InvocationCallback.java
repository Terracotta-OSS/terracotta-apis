package org.terracotta.entity;

/**
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
 *  <li><b>RESULT</b> is the decoded response returned by the server</li>
 *  <li><b>FAILURE</b> is the error that occurred along the way</li>
 * </ul>
 *
 * <p>The client side failure sequence is [sent, failure, complete, retired]</p>
 * <p>The server side sequence is [sent, received, result*, [failure | complete], retired]</p>
 *
 * @param <R>
 */
public interface InvocationCallback<R> {

  default void sent() {}

  default void received() {}

  default void result(R response) {}

  default void failure(Throwable failure) {}

  default void complete() {}

  default void retired() {}
}
