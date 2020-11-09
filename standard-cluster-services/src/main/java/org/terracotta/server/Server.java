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
  /**
   * Current number of servers in the Stripe group.
   *
   * @return server count
   */
  int getServerCount();
  /**
   * The arguments used to start the server.
   *
   * @return
   */
  String[] processArguments();
  /**
   * Stop with server with possible associated actions.
   *
   * @param modes
   */
  void stop(StopAction...modes);
  /**
   * Stop only if the server is currently passive.
   *
   * @param modes
   * @return
   */
  boolean stopIfPassive(StopAction...modes);
  /**
   * Stop only if the server is currently active.
   *
   * @param modes
   * @return
   */
  boolean stopIfActive(StopAction...modes);
  /**
   * is the server currently active.
   *
   * @return
   */
  boolean isActive();
  /**
   * is the server currently stopped.
   *
   * @return
   */
  boolean isStopped();
  /**
   * is the server currently passive and not initialized.
   *
   * @return
   */
  boolean isPassiveUnitialized();
  /**
   * is the server currently passive and synced with an active.
   *
   * @return
   */
  boolean isPassiveStandby();
  /**
   * is the server currently active and in a reconnect window.
   *
   * @return
   */
  boolean isReconnectWindow();
  /**
   * current state of the server.
   *
   * @return
   */
  String getState();
  /**
   * system time when the server was started.
   *
   * @return
   */
  long getStartTime();
  /**
   * system time when the server was activated.
   *
   * @return
   */
  long getActivateTime();
  /**
   * name of the server.
   *
   * @return
   */
  String getIdentifier();
  /**
   * port the clients talk the server.
   *
   * @return
   */
  int getClientPort();
  /**
   * port used for server to server communication.
   *
   * @return
   */
  int getServerPort();
  /**
   * hostname used to configure the server
   * 
   * @return 
   */
  String getServerHostName();
  /**
   * length in seconds for the reconnect window.
   *
   * @return
   */
  int getReconnectWindowTimeout();
  /**
   * Wait for this server to shutdown.
   *
   * @return true when the server should be restarted
   */
  boolean waitUntilShutdown();
  /**
   * write the current cluster state to the logs.
   *
   * @return
   */
  void dump();
  /**
   * The current state of the cluster in string form
   *
   * @return
   */
  String getClusterState();
  /**
   * The current configuration in string form.
   *
   * @return
   */
  String getConfiguration();
  /**
   * The classloader to be used by service extension
   *
   * @return
   */
  ClassLoader getServiceClassLoader(ClassLoader parent, Class<?>...serviceClasses);
  /**
   * The current service implementations for a given service class
   *
   * @return
   */
  <T> List<Class<? extends T>> getImplementations(Class<T> serviceClasses);
  /**
   * Return the JMX subsystem for the server
   *
   * @return
   */
  ServerJMX getManagement();
  /**
   * The client connection properties for this context
   *
   * @return
   */
  Properties getCurrentChannelProperties();
  /**
   * log messages to the console of the server
   * @param message message to send
   * @param sub additional parameters
   */
  void console(String message, Object...sub);
  /**
   * log messages to the warning of the server
   * @param warning message to send
   * @param event additional parameters
   */
  void warn(String warning, Object...event);
  /**
   * log messages to the auditing of the server
   * @param message message to send
   * @param event additional parameters
   */
  void audit(String message, Properties additionalIdInfo);
}
