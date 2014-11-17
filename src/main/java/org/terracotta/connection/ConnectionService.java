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
   * @return true if yes
   */
  boolean handlesURI(URI uri);

  /**
   * Establish a connection to the given URI and with the specified properties. handlesURI() must be called on the URI
   * prior to connect(). Calling connect() on an unverified URI can lead to unspecified behavior.
   *
   * @param uri uri to connect to
   * @param disconnectHandler handler for connection loss
   * @param properties user specified implementation specific properties  @return established connection
   * @throws ConnectionException
   */
  Connection connect(URI uri, final DisconnectHandler disconnectHandler, final Properties properties) throws ConnectionException;
}
