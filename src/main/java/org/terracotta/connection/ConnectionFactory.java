/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 */
package org.terracotta.connection;

import java.net.URI;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * Factory for generating connections to clusters (or pretend connections to pretend clusters).
 */
public final class ConnectionFactory {
  /**
   * Establish a connection based on the given uri. The method will attempt to look for the first suitable implementation
   * of a {@link org.terracotta.connection.ConnectionService} based on whether or not it handles the given URI.
   *
   * @param uri URI to connect to
   * @param properties any configurations to be applied (implementation specific)
   * @return an established connection
   * @throws ConnectionException if there is an error while attempting to connect
   */
  public static Connection connect(URI uri, Properties properties) throws ConnectionException {
    return connect(uri, null, properties);
  }

  /**
   * Establish a connection based on the given uri. The method will attempt to look for the first suitable implementation
   * of a {@link org.terracotta.connection.ConnectionService} based on whether or not it handles the given URI.
   *
   * @param uri URI to connect to
   * @param disconnectHandler handler for when the connection is irrecoverably lost
   * @param properties any configurations to be applied (implementation specific)  @return an established connection
   * @return established connection
   * @throws ConnectionException if there is an error while attempting to connect
   */
  public static Connection connect(URI uri, DisconnectHandler disconnectHandler, Properties properties) throws ConnectionException {
    ServiceLoader<ConnectionService> serviceLoader = ServiceLoader.load(ConnectionService.class,
        ConnectionFactory.class.getClassLoader());
    for (ConnectionService connectionService : serviceLoader) {
      if (connectionService.handlesURI(uri)) {
        return connectionService.connect(uri, disconnectHandler, properties);
      }
    }
    throw new IllegalArgumentException("Unknown URI " + uri);
  }
}
