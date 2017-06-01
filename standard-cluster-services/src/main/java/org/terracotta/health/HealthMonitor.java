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
package org.terracotta.health;

/**
 * A HealthMonitor allows the user to get information about a particular aspect of health monitoring in the platform
 */
public interface HealthMonitor {
  /**
   * The name associated with this HealthMonitor
   * @return a String which identifies this HealthMonitor
   */
  String getName();

  /**
   * Gives the enabled state of this HealthMonitor
   * @return true if and only if this health monitoring is enabled
   */
  boolean isEnabled();

  /**
   * Gets the length of time that pass before a connection can be considered idle.
   * @return the number of milliseconds that can pass without receiving data over a connection before that connection
   * can be considered as idle.
   */
  long getIdleTimeout();
}
