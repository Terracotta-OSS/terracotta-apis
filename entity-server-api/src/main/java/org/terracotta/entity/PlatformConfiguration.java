/*
 * Copyright Terracotta, Inc.
 * Copyright Super iPaaS Integration LLC, an IBM Company 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
