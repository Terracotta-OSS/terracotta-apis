package org.terracotta.connection;

/**
 * @author twu
 */
public interface DisconnectHandler {

  /**
   * Fired when the connection is irrecoverably lost
   */
  void connectionLost();
}
