/*
 * Copyright Terracotta, Inc.
 * Copyright IBM Corp. 2024, 2025
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
package org.terracotta.server;

/**
 *
 */
public class ServerEnv {

  private static Server defaultServer;
  private static final ThreadLocal<Server> threadServer = new ThreadLocal<>();

  public static void setDefaultServer(Server server) {
    defaultServer = server;
  }

  public static Server getDefaultServer() {
    return defaultServer;
  }
  
  public static Server getServer() {
    Server s = threadServer.get();
    if (s == null) {
      if (defaultServer == null) {
        throw new RuntimeException("no server has been set");
      }
      return defaultServer;
    } else {
      return s;
    }
  }

  public static void setServer(Server server) {
    threadServer.set(server);
  }
}
