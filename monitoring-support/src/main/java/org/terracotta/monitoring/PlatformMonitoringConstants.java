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
 *  The Covered Software is Terracotta Core.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.monitoring;

import com.tc.classloader.CommonComponent;


/**
 * Constants associated with the data that a platform implementation is expected to provide via the IMonitoringProducer
 * interface.
 * These constants refer to the places in the tree where platform data is registered.
 */
@CommonComponent
public class PlatformMonitoringConstants {
  /**
   * The name of the top-level node in the tree, used for reporting platform data.
   */
  public static final String PLATFORM_ROOT_NAME = "platform";
  /**
   * The name of the node in the tree which is the parent to all nodes representing connected servers.
   */
  public static final String SERVER_ROOT_NAME = "servers";
  /**
   * The name of the node in the tree which is the parent to all nodes representing connected clients.
   */
  public static final String CLIENTS_ROOT_NAME = "clients";
  /**
   * The name of the node in the tree which is the parent to all nodes representing entities.
   */
  public static final String ENTITIES_ROOT_NAME = "entities";
  /**
   * The name of the node in the tree which is the parent to all nodes representing client-entity fetched connections.
   */
  public static final String FETCHED_ROOT_NAME = "fetched";
  /**
   * The name of the node in the tree which stores the name of the server's current state.
   */
  public static final String STATE_NODE_NAME = "state";

  /**
   * The path of the platform node, for manipulating its children.
   */
  public static final String[] PLATFORM_PATH = {PLATFORM_ROOT_NAME};
  /**
   * The path of the platform's clients node, for manipulating its children.
   */
  public static final String[] CLIENTS_PATH = {PLATFORM_ROOT_NAME, CLIENTS_ROOT_NAME};
  /**
   * The path of the platform's entities node, for manipulating its children.
   */
  public static final String[] ENTITIES_PATH = {PLATFORM_ROOT_NAME, ENTITIES_ROOT_NAME};
  /**
   * The path of the platform's fetched node, for manipulating its children.
   */
  public static final String[] FETCHED_PATH = {PLATFORM_ROOT_NAME, FETCHED_ROOT_NAME};
  /**
   * The path of the platform's state node, for reading its value.
   */
  public static final String[] STATE_PATH = {PLATFORM_ROOT_NAME, STATE_NODE_NAME};

  /**
   * Set in STATE_NODE_NAME when the server enters a stopped state (typically only during startup).
   */
  public static final String SERVER_STATE_STOPPED = "STOPPED";
  /**
   * Set in STATE_NODE_NAME when the server enters the active state.
   */
  public static final String SERVER_STATE_ACTIVE = "ACTIVE";
  /**
   * Set in STATE_NODE_NAME when the server enters the passive state.
   */
  public static final String SERVER_STATE_PASSIVE = "PASSIVE";
  /**
   * Set in STATE_NODE_NAME when the server is new and has no cluster state.
   */
  public static final String SERVER_STATE_UNINITIALIZED = "UNINITIALIZED";
}