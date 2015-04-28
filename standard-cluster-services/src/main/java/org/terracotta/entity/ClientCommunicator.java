package org.terracotta.entity;

import java.util.concurrent.Future;

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
  void sendNoResponse(ClientID clientID, byte[] payload);

  /**
   * Send a message getting an async completion back.
   *
   * @param clientID client to send to
   * @param payload bytes to send
   * @return Future representing when the client finishes with and acknowledges the sent message.
   */
  Future<Void> send(ClientID clientID, byte[] payload);
}
