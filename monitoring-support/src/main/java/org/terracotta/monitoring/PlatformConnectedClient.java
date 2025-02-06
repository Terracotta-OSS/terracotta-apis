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
package org.terracotta.monitoring;

import java.io.Serializable;
import java.net.InetAddress;


/**
 * A type which describes a client connected to the server.
 */
public class PlatformConnectedClient implements Serializable {
  private static final long serialVersionUID = -8750564835447846768L;

  public InetAddress localAddress;
  public int localPort;
  public InetAddress remoteAddress;
  public int remotePort;
  public long clientPID;
  public String uuid;
  public String name;

  public PlatformConnectedClient() {
    // For Serializable.
  }

  public PlatformConnectedClient(String uuid, String name, InetAddress localAddress, int localPort, InetAddress remoteAddress, int remotePort, long clientPID) {
    this.uuid = uuid;
    this.name = name;
    this.localAddress = localAddress;
    this.localPort = localPort;
    this.remoteAddress = remoteAddress;
    this.remotePort = remotePort;
    this.clientPID = clientPID;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("PlatformConnectedClient{");
    sb.append("clientPID=").append(clientPID);
    sb.append(", localAddress=").append(localAddress);
    sb.append(", localPort=").append(localPort);
    sb.append(", remoteAddress=").append(remoteAddress);
    sb.append(", remotePort=").append(remotePort);
    sb.append(", uuid='").append(uuid).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public int hashCode() {
    return this.localAddress.hashCode()
        ^ localPort
        ^ this.remoteAddress.hashCode()
        ^ remotePort
        ^ (int)clientPID
        ^ this.uuid.hashCode()
        ^ this.name.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    boolean doesMatch = (this == other);
    if (!doesMatch && (null != other) && (getClass() == other.getClass()))
    {
      final PlatformConnectedClient that = (PlatformConnectedClient) other;
      doesMatch = this.localAddress.equals(that.localAddress)
          && (this.localPort == that.localPort)
          && this.remoteAddress.equals(that.remoteAddress)
          && (this.remotePort == that.remotePort)
          && (this.clientPID == that.clientPID)
          && this.uuid.equals(that.uuid)
          && this.name.equals(that.name);
    }
    return doesMatch;
  }
}
