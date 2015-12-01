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
 */
public interface InvocationBuilder {
  /**
   * Requests that the invocation wait on the SENT acknowledgement, before returning from invoke().
   * 
   * @return Itself
   */
  public InvocationBuilder ackSent();

  /**
   * Requests that the invocation wait on the RECEIVED acknowledgement, before returning from invoke().
   * 
   * @return Itself
   */
  public InvocationBuilder ackReceived();

  /**
   * Requests that the invocation wait on the COMPLETED acknowledgement, before returning from invoke().
   * 
   * @return Itself
   */
  public InvocationBuilder ackCompleted();

  /**
   * Sets whether or not the invocation should be replicated to any passive servers in this stripe.
   * 
   * @param requiresReplication True if the message should be replicated, false otherwise
   * @return Itself
   */
  public InvocationBuilder replicate(boolean requiresReplication);

  /**
   * Sets the raw payload of the invocation.
   * 
   * @param payload The raw contents of the invocation
   * @return Itself
   */
  public InvocationBuilder payload(byte[] payload);

  /**
   * Actually sends the invocation staged in the receiver to the server.  Note that this will wait for any requested
   * acknowledgements before returning.
   * 
   * @return The asynchronous result of the invocation
   */
  public InvokeFuture<byte[]> invoke();
}
