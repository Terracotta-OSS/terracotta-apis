package org.terracotta.entity;

public interface InvokeContext {
  /**
   * source instance from which the invocation originates.
   *
   * @return descriptor
   */
  ClientDescriptor getClientDescriptor();

  /**
   * current id for this client that this invoke is part of
   *
   * @return txid, or -1 if unknown.
   */
  long getCurrentTransactionId();

  /**
   * earliest active id for this client; id's older than this
   * can be thrown away.
   *
   * @return txid, or -1 if unknown.
   */
  long getOldestTransactionId();
}
