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

/**
 *
 */
public class ServerState {
  private final String state;
  private final long timestamp;
  private final long activate;

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
}
