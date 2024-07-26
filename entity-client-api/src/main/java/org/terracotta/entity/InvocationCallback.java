/*
 * Copyright Terracotta, Inc.
 * Copyright Super iPaaS Integration LLC, an IBM Company 2024
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

/**
 * <p>The acknowledgement configurations can be complex so a high-level description of them and their meanings is
 * provided in the relevant methods.</p>
 *
 * <p>The client side failure sequence is <code>sent, failure*, complete, retired</code></p>
 * <p>The server side sequence is <code>sent, received, result*, [failure* | complete], retired</code></p>
 *
 * @param <R> the server response type.
 */
public interface InvocationCallback<R> {

  enum Types {
    SENT, RECEIVED,
    RESULT, FAILURE,
    COMPLETE, RETIRED
  }

  /**
   * <b>SENT</b> means that a message has become "in-flight", meaning that it will be re-sent in the event of a
   * server restart or fail-over to passive. Note that this message is not generally useful to applications.
   */
  default void sent() {
  }

  /**
   * <b>RECEIVED</b> if requested, will only return from invoke() once the global order of this message (relative to
   * others depending on RECEIVED) has been determined for the entire stripe. This means that every server in the stripe
   * has received the message, and agreed upon the order. Note that RECEIVED is the only optional ack (the others always
   * go over the wire, but only optionally influence client-side waiting). This should be requested in cases where
   * knowing the global order of messages (preserved for restart or fail-over) is important.
   */
  default void received() {
  }

  /**
   * <b>RESULT</b> is a decoded response returned by the server. Note that a single invocation can result in zero, one
   * or many responses.
   *
   * @param response the decoded server response.
   */
  default void result(R response) {
  }

  /**
   * <b>FAILURE</b> is an error that occurred along the way. Note that a single invocation can result in zero, one
   * or many failures.
   *
   * @param failure the exception that was thrown along the way.
   */
  default void failure(Throwable failure) {
  }

  /**
   * <b>COMPLETED</b> is observed by the client when the active has finished running the invoke and returned the
   * result. Note that this only relates to what has happened on the active: it is possible for the client to see the
   * COMPLETED ack before any passives have seen the message.
   */
  default void complete() {
  }

  /**
   * <b>RETIRED</b> is the final acknowledgement, after which time the message will not be re-sent in the event of
   * restart or fail-over. It represents the point where the message has completed on all servers in the stripe and also
   * any messages it created to defer its retirement (see {@link IEntityMessenger}) have also completed on all servers in
   * the stripe.
   */
  default void retired() {
  }
}
