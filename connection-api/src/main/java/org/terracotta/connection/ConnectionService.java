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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Service for establishing connections. The expectation is that either of the two ways will be used:
 * <ul>
 * <li> {@link #handlesURI(URI)} will be called with a candidate URI to see if this service handles it. If {@code handlesURI}
 * returns true, {@code connect()} will be called passing in the URI again along with the user-specified properties. OR,</li>
 * <li> {@link #handlesConnectionType(String)} will be called with a candidate connection type to see if this service handles it.
 * If {@code handlesConnectionType} returns true, {@code connect()} will be called passing in the servers along with the
 * user-specified properties.</li>
 * </ul>
 * <p>
 * If any error occurs during the connection process, a {@link ConnectionException} will be thrown.
 *
 * @author twu
 */
public interface ConnectionService {

  /**
   * Check if the given URI can be handled by this ConnectionService.
   *
   * @param uri uri to check
   * @return true if supported
   */
  boolean handlesURI(URI uri);

  /**
   * Check if the connection with the given type is handled by this ConnectionService.
   *
   * @param connectionType connectionType to check
   * @return true if supported
   */
  default boolean handlesConnectionType(String connectionType) {
      return true;
  }

  /**
   * Establish a connection to the given URI and with the specified properties. handlesURI() must be called on the URI
   * prior to connect(). Calling connect() on an unverified URI can lead to unspecified behavior.
   *
   * @param uri uri to connect to
   * @param properties user specified implementation specific properties  @return established connection
   * @return established connection
   * @throws ConnectionException on connection failure
   */
  Connection connect(URI uri, Properties properties) throws ConnectionException;

  /**
   * Establish a connection to the given servers using the specified properties. handlesConnectionType() must be
   * called with the type prior to connect(). Calling connect() on an unverified type can lead to unspecified behavior.
   *
   * @param servers servers to connect to
   * @param properties user specified implementation specific properties
   * @return established connection
   * @throws ConnectionException on connection failure
   */
  default Connection connect(Iterable<InetSocketAddress> servers, Properties properties) throws ConnectionException {
      StringBuilder b = new StringBuilder("terracotta://");
      for (InetSocketAddress a : servers) {
          b.append(a.getHostString());
          b.append(':');
          b.append(a.getPort());
          b.append(',');
      }
      try {
          if (b.length() > 0) {
            return connect(new URI(b.substring(0, b.length()-1)), properties);
          } else {
              throw new ConnectionException(new IOException("no servers specified"));
          }
      } catch (URISyntaxException u) {
          throw new ConnectionException(u);
      }
  }
}
