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

import java.net.URI;
import java.util.Properties;

/**
 * Service for establishing connections. The expectation is that these steps will be followed:
 *
 * 1. handlesURI() will be called with a candidate URI to see if this service handles it
 * 2. if handlesURI() returned true, connect() will be called passing in the URI again and user specified properties.
 *
 * If any error occurs during the connection process, a ConnectionException should be thrown.
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
   * Establish a connection to the given URI and with the specified properties. handlesURI() must be called on the URI
   * prior to connect(). Calling connect() on an unverified URI can lead to unspecified behavior.
   *
   * @param uri uri to connect to
   * @param properties user specified implementation specific properties  @return established connection
   * @return established connection
   * @throws ConnectionException on connection failure
   */
  Connection connect(URI uri, Properties properties) throws ConnectionException;
}
