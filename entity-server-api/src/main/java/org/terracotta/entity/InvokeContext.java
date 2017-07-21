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

  /**
   * Does the client information (ClientDescriptor and transaction ids)
   * represent an actual valid client endpoint information or is it merely a marker.
   * @return true if the client information represents a real client.
   */
  boolean isValidClientInformation();

  /**
   * Create a tight byte representation of a client descriptor
   * @param clientDescriptor
   * @return
   */
  byte[] getClientDescriptorAsBytes(ClientDescriptor clientDescriptor);

  /**
   * Create a client descriptor from a byte array representation.
   * @param arr
   * @return
   */
  ClientDescriptor createClientDescriptorFromBytes(byte[] arr);
}
