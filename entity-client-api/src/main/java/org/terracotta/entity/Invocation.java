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

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.EnumSet.of;
import static org.terracotta.entity.InvocationCallback.Types.COMPLETE;
import static org.terracotta.entity.InvocationCallback.Types.FAILURE;
import static org.terracotta.entity.InvocationCallback.Types.RECEIVED;
import static org.terracotta.entity.InvocationCallback.Types.RESULT;
import static org.terracotta.entity.InvocationCallback.Types.RETIRED;
import static org.terracotta.entity.InvocationCallback.Types.SENT;

/**
 * A representation of an invocation against a server-side entity.
 * <p>
 * Note that an invocation isn't sent to the server until one of the invoke methods is called.</p>
 *
 * @param <R> the returned response type
 */
public interface Invocation<R> {

  /**
   * Sends the configured message to the server.
   *
   * The <code>callback</code> will get called along the way each time an interesting acknowledgement is received.
   *
   * @param callback the callback on which to notify the acknowledgements.
   * @param interests the set of acknowledgements the invocation is interested in
   * @return a task representing the invocation of the message
   */
  Task invoke(InvocationCallback<R> callback, Set<InvocationCallback.Types> interests);

  /*
   * Sends the configured message to the server returning a future representing the completion of the invocation.
   *
   * @return a future that completes when the invocation is complete
   */
  default Future<R> invoke() {
    return invokeAnd(COMPLETE);
  }

  /*
   * Sends the configured message to the server returning a future represent the retirement of the invocation.
   *
   * @return a future that completes when the message is retired
   */
  default Future<R> invokeAndRetire() {
    return invokeAnd(RETIRED);
  }

  /*
   * Sends the configured message to the server returning a future representing the requested acknowledgement.
   *
   * @param waitFor the acknowledgement to wait for
   * @return a future that completes when the given acknowledgement is received
   */
  default Future<R> invokeAnd(InvocationCallback.Types waitFor) {
    CompletableFuture<R> future = new CompletableFuture<>();

    Task task = invoke(new InvocationCallback<R>() {

      private volatile R response;

      @Override
      public void sent() {
        notify(SENT);
      }

      @Override
      public void received() {
        notify(RECEIVED);
      }

      @Override
      public void result(R response) {
        this.response = response;
        notify(RESULT);
      }

      @Override
      public void complete() {
        notify(COMPLETE);
      }

      @Override
      public void retired() {
        notify(RETIRED);
      }

      private void notify(Types callback) {
        if (callback.equals(waitFor)) {
          future.complete(response);
        }
      }

      @Override
      public void failure(Throwable failure) {
        future.completeExceptionally(failure);
      }
    }, of(RESULT, FAILURE, waitFor));

    return new Future<R>() {

      @Override
      public boolean cancel(boolean mayInterruptIfRunning) {
        return task.cancel() && future.cancel(mayInterruptIfRunning);
      }

      @Override
      public boolean isCancelled() {
        return future.isCancelled();
      }

      @Override
      public boolean isDone() {
        return future.isDone();
      }

      @Override
      public R get() throws InterruptedException, ExecutionException {
        return future.get();
      }

      @Override
      public R get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
      }
    };
  }

  /**
   * Synchronously retrieve the provided future, unwrapping any thrown {@code ExecutionException}.
   * <p>
   * Unchecked exception types are rethrown directly.
   * Checked exceptions are rewrapped with RuntimeException instances.
   *
   * @param future the future to retrieve
   * @return the result of the future
   * @param <R> return the type of the future
   * @throws InterruptedException if the current thread was interrupted while waiting
   */
  static <R> R synchronouslyGet(Future<R> future) throws InterruptedException {
    return synchronouslyGet(future, RuntimeException.class);
  }

  /**
   * Synchronously retrieve the provided future, unwrapping any thrown {@code ExecutionException}.
   * <p>
   * Unchecked exception types and instances of {@code T} are rethrown directly.
   * Checked exceptions are rewrapped with RuntimeException instances.
   *
   * @param future the future to retrieve
   * @param propagate the concrete checked exception type to propagate
   * @return the result of the future
   * @param <R> return the type of the future
   * @param <T> return the propagated checked exception type
   * @throws InterruptedException if the current thread was interrupted while waiting
   * @throws T if the operation threw an instance of {@code T} during execution
   */
  static <R, T extends Throwable> R synchronouslyGet(Future<R> future, Class<T> propagate) throws InterruptedException, T {
    try {
      return future.get();
    } catch (ExecutionException e) {
      Throwable cause = e.getCause();
      if (cause instanceof Error) {
        throw (Error) cause;
      } else if (cause instanceof RuntimeException) {
        throw (RuntimeException) cause;
      } else if (propagate.isInstance(cause)) {
        throw propagate.cast(cause);
      } else {
        throw new RuntimeException(cause);
      }
    }
  }

  /**
   * Synchronously retrieve the provided future with the given timeout, unwrapping any thrown {@code ExecutionException}.
   * <p>
   * Unchecked exception types are rethrown directly.
   * Checked exceptions are rewrapped with RuntimeException instances.
   *
   *
   * @param future the future to retrieve
   * @param timeout the maximum time to wait
   * @param unit the time unit of the timeout argument
   * @return the result of the future
   * @param <R> return the type of the future
   * @throws InterruptedException if the current thread was interrupted while waiting
   */
  static <R> R synchronouslyGet(Future<R> future, long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
    return synchronouslyGet(future, timeout, unit, RuntimeException.class);
  }

  /**
   * Synchronously retrieve the provided future with the given timeout, unwrapping any thrown {@code ExecutionException}.
   * <p>
   * Unchecked exception types and instances of {@code T} are rethrown directly.
   * Checked exceptions are rewrapped with RuntimeException instances.
   *
   * @param future the future to retrieve
   * @param timeout the maximum time to wait
   * @param unit the time unit of the timeout argument
   * @param propagate the concrete checked exception type to propagate
   * @return the result of the future
   * @param <R> return the type of the future
   * @param <T> return the propagated checked exception type
   * @throws InterruptedException if the current thread was interrupted while waiting
   * @throws T if the operation threw an instance of {@code T} during execution
   */
  static <R, T extends Throwable> R synchronouslyGet(Future<R> future, long timeout, TimeUnit unit, Class<T> propagate) throws InterruptedException, TimeoutException, T {
    try {
      return future.get(timeout, unit);
    } catch (ExecutionException e) {
      Throwable cause = e.getCause();
      if (cause instanceof Error) {
        throw (Error) cause;
      } else if (cause instanceof RuntimeException) {
        throw (RuntimeException) cause;
      } else if (propagate.isInstance(cause)) {
        throw propagate.cast(cause);
      } else {
        throw new RuntimeException(cause);
      }
    }
  }

  /**
   * Uninterruptibly retrieve the provided future, unwrapping any thrown {@code ExecutionException}.
   * <p>
   * Unchecked exception types are rethrown directly.
   * Checked exceptions are rewrapped with RuntimeException instances.
   *
   * @param future the future to retrieve
   * @return the result of the future
   * @param <R> return the type of the future
   */
  static <R> R uninterruptiblyGet(Future<R> future) {
    return uninterruptiblyGet(future, RuntimeException.class);
  }

  /**
   * Uninterruptibly retrieve the provided future, unwrapping any thrown {@code ExecutionException}.
   * <p>
   * Unchecked exception types and instances of {@code T} are rethrown directly.
   * Checked exceptions are rewrapped with RuntimeException instances.
   *
   * @param future the future to retrieve
   * @param propagate the concrete checked exception type to propagate
   * @return the result of the future
   * @param <R> return the type of the future
   * @param <T> return the propagated checked exception type
   * @throws T if the operation threw an instance of {@code T} during execution
   */
  static <R, T extends Throwable> R uninterruptiblyGet(Future<R> future, Class<T> propagate) throws T {
    boolean interrupted = Thread.interrupted();
    try {
      while (true) {
        try {
          return synchronouslyGet(future, propagate);
        } catch (InterruptedException e) {
          interrupted = true;
        }
      }
    } finally {
      if (interrupted) {
        Thread.currentThread().interrupt();
      }
    }
  }

  /**
   * Represents the invocation of message.
   */
  interface Task {
    /**
     * Attempt to cancel the associated invocation.
     * <p>
     * A {@code true} return indicates that the invocation has been cancelled, and its effects will not be seen in the
     * future cluster state.
     * A {code false} return indicates that the invocation is not known to be cancelled. Its effects may or may not be
     * seend in any future cluster state.
     *
     * @return {@code true} if the message is known to be successfully cancelled.
     */
    boolean cancel();
  }
}
