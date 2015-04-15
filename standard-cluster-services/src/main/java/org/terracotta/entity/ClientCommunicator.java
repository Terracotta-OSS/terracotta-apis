package org.terracotta.entity;

/**
 * Communicator allowing server-side entities to push messages to client-side entities.
 *
 * @author twu
 */
public interface ClientCommunicator {

  /**
   * Send a message to the client-side of an entity.
   *
   * @param clientID client to send to
   * @param payload bytes to send
   */
  void send(ClientID clientID, byte[] payload);
}
