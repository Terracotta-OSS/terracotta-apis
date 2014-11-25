package org.terracotta.entity;

/**
 * @author twu
 */
public interface EndpointListener {

  /**
   * Handle a message coming from the clustered implementation of the Entity
   *
   * @param payload serialized message
   */
  void handleMessage(byte[] payload);
}
