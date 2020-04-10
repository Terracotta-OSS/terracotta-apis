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
 *  The Covered Software is Terracotta API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.server;

import java.util.List;
import java.util.Properties;

/**
 *
 */
public interface Server {
  int getServerCount();
  
  String[] processArguments();

  void stop(StopAction...modes);

  boolean stopIfPassive(StopAction...modes);

  boolean stopIfActive(StopAction...modes);

  boolean isActive();

  boolean isStopped();

  boolean isPassiveUnitialized();

  boolean isPassiveStandby();

  boolean isReconnectWindow();

  String getState();

  long getStartTime();

  long getActivateTime();

  String getIdentifier();

  int getClientPort();

  int getServerPort();

  int getReconnectWindowTimeout();

  void waitUntilShutdown();

  void dump();

  String getResourceState();

  String getClusterState();

  String getConfiguration();

  ClassLoader getServiceClassLoader(ClassLoader parent, Class<?>...serviceClasses);

  <T> List<Class<? extends T>> getImplementations(Class<T> serviceClasses);

  ServerJMX getManagement();

  Properties getCurrentChannelProperties();

  void warn(String warning, Object...event);
}
