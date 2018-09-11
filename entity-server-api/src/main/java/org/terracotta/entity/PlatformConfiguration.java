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
 *  The Covered Software is Entity API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.entity;

import java.util.Collection;


/**
 * An API for reading the configuration of the current server.
 */
public interface PlatformConfiguration {
  /**
   * Gets the configured name for this server
   *
   * @return configured server name
   */
  String getServerName();

  /**
   * Gets the configured host for this server
   *
   * @return configured host
   */
  default String getHost() {
      return "localhost";
  }

  /**
   * Gets the configured tsa port for this server
   *
   * @return configured port
   */
  default int getTsaPort() {
      return 9510;
  }

  /**
   * The extended configuration objects plugged into the server
   * 
   * @param <T> type of configuration object to retrieve 
   * @param type supplied type
   * @return a collection of objects configured via the plug-in system of the type specified
   */
  <T> Collection<T> getExtendedConfiguration(Class<T> type);
}
