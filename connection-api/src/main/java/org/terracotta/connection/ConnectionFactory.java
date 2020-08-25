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
 *  The Covered Software is Connection API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.connection;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Factory for generating connections to stripe.
 */
public final class ConnectionFactory {
  /**
   * Establish a connection based on the given uri.
   * <p>
   * The method will attempt to look for the first suitable implementation of a {@link org.terracotta.connection.ConnectionService}
   * based on whether or not it handles the given URI. {@link #connect(Iterable, Properties)} is preferable to this method,
   * as the former does not involve extra parsing and enables you to provide multiple IPv6 addresses to connect to.
   *
   * @param uri        URI to connect to
   * @param properties any configurations to be applied (implementation specific)
   * @return an established connection
   * @throws ConnectionException if there is an error while attempting to connect
   * @see #connect(Iterable, Properties)
   * @see #connect(Supplier, Properties)
   * @deprecated use {@link #connect(Supplier, Properties)} instead
   */
  @Deprecated
  public static Connection connect(URI uri, Properties properties) throws ConnectionException {
    validateURI(uri);
    return getConnectionService(uri).connect(uri, properties);
  }

  /**
   * Establish a connection to the provided servers.
   * <p>
   * The method will attempt to look for the first suitable implementation of a {@link org.terracotta.connection.ConnectionService}
   * based on whether or not it handles the given connection type.
   * This method is preferable to {@link #connect(URI, Properties)}, as it does not involve extra parsing and enables
   * you to provide multiple IPv6 addresses to connect to.
   * <p>
   * {@link InetSocketAddress#createUnresolved(String, int)} can be used to populate an {@code Iterable} to be passed to
   * this method. If the default server port is to be used, a value of 0 can be passed as the second argument to
   * {@link InetSocketAddress#createUnresolved(String, int)}.
   *
   * @param servers    servers to connect to
   * @param properties any configurations to be applied (implementation specific), including a connection type
   * @return an established connection
   * @throws ConnectionException if there is an error while attempting to connect
   * @see #connect(URI, Properties)
   * @see #connect(Supplier, Properties)
   * @deprecated use {@link #connect(Supplier, Properties)} instead
   */
  @Deprecated
  public static Connection connect(Iterable<InetSocketAddress> servers, Properties properties) throws ConnectionException {
    return getConnectionService(properties).connect(servers, properties);
  }

  /**
   * Establishes a connection to the set of servers provided by the supplier using the given properties.
   *
   * <p>
   * The method will attempt to look for the first suitable implementation of a {@link org.terracotta.connection.ConnectionService}
   * based on whether or not it handles the given connection type. Connection type can be specified using
   * {@code properties} argument with key name {@link ConnectionPropertyNames#CONNECTION_TYPE}
   *
   * <p>
   * This connect variant adds support for dynamic topologies. {@code serverAddressesSupplier} should return the latest
   * set of servers and should not block, it is used for getting the set of servers during initial connection and
   * failover reconnect.
   *
   * @param serverAddressesSupplier supplier that provides a set of servers to establish connection
   * @param properties any configurations to be applied (implementation specific), including a connection type
   * @return an established connection
   * @throws ConnectionException if there is an error while attempting to connect
   */
  public static Connection connect(Supplier<Set<InetSocketAddress>> serverAddressesSupplier, Properties properties) throws ConnectionException {
    return getConnectionService(properties).connect(serverAddressesSupplier, properties);
  }

  private static void validateURI(URI uri) throws ConnectionException {
    try {
      URIUtils.validateTerracottaURI(uri);
    } catch (URISyntaxException e) {
      throw new ConnectionException(e);
    }
  }

  private static ConnectionService getConnectionService(Properties properties) {
    String connectionType = properties.getProperty(ConnectionPropertyNames.CONNECTION_TYPE, "terracotta");
    for (ConnectionService connectionService : getServiceLoader()) {
      if (connectionService.handlesConnectionType(connectionType)) {
        return connectionService;
      }
    }
    throw new IllegalArgumentException("Unknown connection type " + connectionType);
  }

  private static ConnectionService getConnectionService(URI uri) {
    for (ConnectionService connectionService : getServiceLoader()) {
      if (connectionService.handlesURI(uri)) {
        return connectionService;
      }
    }
    throw new IllegalArgumentException("Unknown URI " + uri);
  }

  private static ServiceLoader<ConnectionService> getServiceLoader() {
    return ServiceLoader.load(ConnectionService.class, ConnectionFactory.class.getClassLoader());
  }
}
