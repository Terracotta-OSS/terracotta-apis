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

import java.io.Serializable;


public class ServerState implements Serializable {
  private static final long serialVersionUID = 1207854911088611229L;

  private String state;
  private long timestamp;
  private long activate;

  public ServerState() {
    // For Serializable.
  }

  public ServerState(String state, long timestamp, long activate) {
    this.state = state;
    this.timestamp = timestamp;
    this.activate = activate;
  }

  public String getState() {
    return state;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public long getActivate() {
    return activate;
  }

  @Override
  public String toString() {
    return "ServerState{" + "state=" + state + ", timestamp=" + timestamp + ", activateTime=" + activate +  '}';
  }

  @Override
  public int hashCode() {
    return this.state.hashCode()
        ^ (int)timestamp
        ^ (int)activate;
  }

  @Override
  public boolean equals(Object other) {
    boolean doesMatch = (this == other);
    if (!doesMatch && (null != other) && (getClass() == other.getClass()))
    {
      final ServerState that = (ServerState) other;
      doesMatch = this.state.equals(that.state)
          && (this.timestamp == that.timestamp)
          && (this.activate == that.activate);
    }
    return doesMatch;
  }
}
