package org.terracotta.entity;

import java.util.Collection;

/**
 *
 * API for exposing platform configuration
 *
 * @author vmad
 */
public interface PlatformConfiguration {
  /**
   * Gets configured name for this server
   *
   * @return configured server name
   */
  String getServerName();
  /**
   * The extended configuration objects plugged into the server
   * 
   * @param <T> type of configuration object to retrieve 
   * @param type supplied type
   * @return a collection of objects configured via the plug-in system of the type specified
   */
  <T> Collection<T> getExtendedConfiguration(Class<T> type);
}
