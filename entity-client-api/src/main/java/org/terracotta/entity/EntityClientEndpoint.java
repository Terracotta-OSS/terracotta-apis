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

import java.io.Closeable;
import java.util.concurrent.Future;


/**
 * The common connection point between client-side entity instances and the underlying connection to the server.
 * Note that instances of this class become invalid after close() and will throw IllegalStateException on access.
 *
 * @param <M> An {@link EntityMessage}
 * @param <R> An {@link EntityResponse}
 */
public interface EntityClientEndpoint<M extends EntityMessage, R extends EntityResponse> extends Closeable {
  /**
   * Requests the configuration of the server-side entity to which the receiver is attached.  Note that this refers to the
   * configuration originally passed to the server-side entity when it was instantiated.
   * 
   * @return The server-side entity's initial configuration
   */
  byte[] getEntityConfiguration();

  /**
   * Sets the delegate for events originating within the end-point.
   * Note that this value can only be set once.
   * 
   * @param delegate The delegate to use for events originating within the receiver.
   */
  void setDelegate(EndpointDelegate<R> delegate);

  /**
   * Called to create an invocation to send to the remote server-side entity.
   * Note that this doesn't actually send the invocation.  That is done by the returned {@code Invocation} instance,
   * once the invocation is fully formed.
   * 
   * @return An InvocationBuilder instance to build a new invocation to send to the server-side instance
   */
  Invocation<R> message(M message);

  /**
   * The instance will be unusable after this call.
   */
  @Override
  void close();
  /**
   *
   * Release the endpoint asynchronously.  The endpoint will be released some time in the future.
   * @return a future void that completes when all the endpoints invokes are flushed and the reference released from
   * the server
   * <i>The returned future can throw ExecutionException with a wrapped exception if there was some problem closing the entity</i>
   *
   */
  Future<Void> release();
}
