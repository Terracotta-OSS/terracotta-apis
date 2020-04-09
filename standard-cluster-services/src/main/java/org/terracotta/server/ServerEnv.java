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
      return defaultServer;
    } else {
      return s;
    }
  }

  public static void setServer(Server server) {
    threadServer.set(server);
  }
}
