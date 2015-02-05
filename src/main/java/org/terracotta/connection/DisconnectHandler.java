package org.terracotta.connection;

import java.net.URI;

/**
 * @author twu
 */
public interface DisconnectHandler {

  /**
   * Fired when the connection is irrecoverably lost
   *
   * @param uri uri of the lost connection
   */
  void connectionLost(URI uri);
}
