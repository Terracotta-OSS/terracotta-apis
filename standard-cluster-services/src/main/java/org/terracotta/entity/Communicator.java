package org.terracotta.entity;

/**
 * Communicator allowing server-side entities to push messages to client-side entities.
 *
 * @author twu
 */
public interface Communicator {

  /**
   * Send a message in the reverse direction (to a source) expecting no response.
   *
   * @param sourceID source to send to
   * @param payload bytes to send
   */
  void send(SourceID sourceID, byte[] payload);
}
