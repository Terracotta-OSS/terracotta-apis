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
package org.terracotta.passthrough;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.terracotta.entity.InvokeFuture;
import org.terracotta.exception.EntityException;


/**
 * Used by the client-side message processing to handle the synchronous nature of the messaging system.  This expects the
 * client code's thread to block on acks or completion, and be unblocked by the client-send message processing thread
 * processing the corresponding acks and completion messages.
 */
public class PassthroughWait implements InvokeFuture<byte[]> {
  // Save the information used to reset this object on resend.
  private byte[] rawMessageForResend;
  private final boolean shouldWaitForReceived;
  private final boolean shouldWaitForCompleted;
  // Note that the point where we wait for acks isn't exposed outside the InvokeFuture interface so this set of waiting
  // threads only applies to those threads waiting to get a response.
  private final Set<Thread> waitingThreads;
  
  // The active state of the instance after the send.
  private boolean waitingForSent;
  private boolean waitingForReceive;
  private boolean waitingForComplete;
  private boolean didComplete;
  private byte[] response;
  private EntityException error;

  public PassthroughWait(boolean shouldWaitForSent, boolean shouldWaitForReceived, boolean shouldWaitForCompleted) {
    this.shouldWaitForReceived = shouldWaitForReceived;
    this.shouldWaitForCompleted = shouldWaitForCompleted;
    this.waitingThreads = new HashSet<Thread>();
    
    this.waitingForSent = shouldWaitForSent;
    this.waitingForReceive = shouldWaitForReceived;
    this.waitingForComplete = shouldWaitForCompleted;
    this.didComplete = false;
    this.response = null;
    this.error = null;
  }
  
  public synchronized void waitForAck() {
    boolean interrupted = false;
    while (this.waitingForSent || this.waitingForReceive || this.waitingForComplete) {
      try {
        wait();
      } catch (InterruptedException e) {
        // Note that we can't be interrupted when waiting for acks 
        interrupted = true;
      }
    }
    if (interrupted) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public synchronized void interrupt() {
    for (Thread waitingThread : this.waitingThreads) {
      waitingThread.interrupt();
    }
  }

  @Override
  public synchronized boolean isDone() {
    return this.didComplete;
  }

  @Override
  public synchronized byte[] get() throws InterruptedException, EntityException {
    Thread callingThread = Thread.currentThread();
    boolean didAdd = this.waitingThreads.add(callingThread);
    // We can't have already been waiting.
    Assert.assertTrue(didAdd);
    try {
      while (!this.didComplete) {
        this.wait();
      }
    } finally {
      // We will hit this path on interrupt, for example.
      this.waitingThreads.remove(callingThread);
    }
    if (null != this.error) {
      throw this.error;
    }
    return this.response;
  }

  @Override
  public byte[] getWithTimeout(long timeout, TimeUnit unit) throws InterruptedException, EntityException, TimeoutException {
    return get();
  }

  public synchronized void sent() {
    this.waitingForSent = false;
    notifyAll();
  }

  public synchronized void handleAck() {
    this.waitingForReceive = false;
    notifyAll();
  }

  public synchronized void handleComplete(byte[] result, EntityException error) {
    this.waitingForComplete = false;
    this.didComplete = true;
    this.response = result;
    this.error = error;
    notifyAll();
  }

  public void saveRawMessageForResend(byte[] raw) {
    this.rawMessageForResend = raw;
  }

  /**
   * Resets the ACK wait state for the receiver and returns the raw message for the caller to re-send.
   */
  public byte[] resetAndGetMessageForResend() {
    this.waitingForReceive = this.shouldWaitForReceived;
    this.waitingForComplete = this.shouldWaitForCompleted;
    this.didComplete = false;
    this.response = null;
    this.error = null;
    return this.rawMessageForResend;
  }
}
