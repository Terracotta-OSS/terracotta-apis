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
package org.terracotta.monitoring;

import java.io.Serializable;



public class PlatformServer implements Serializable {
  private static final long serialVersionUID = 6603745920231301851L;

  private String serverName;
  private String hostName;
  private String hostAddress;
  private String bindAddress;
  private int bindPort;
  private int groupPort;
  private String version;
  private String build;
  private long startTime;

  public PlatformServer() {
    // For Serializable.
  }

  public PlatformServer(String serverName, String host, String hostAddress, String bindAddress, int bindPort, int groupPort, String version, String build, long startTime) {
    this.serverName = serverName;
    this.hostName = host;
    this.hostAddress = hostAddress;
    this.bindAddress = bindAddress;
    this.bindPort = bindPort;
    this.groupPort = groupPort;
    this.version = version;
    this.build = build;
    this.startTime = startTime;
  }
  
  public String getHostAddress() {
    return hostAddress;
  }

  public String getServerName() {
    return serverName;
  }

  public String getHostName() {
    return hostName;
  }

  public String getBindAddress() {
    return bindAddress;
  }

  public int getBindPort() {
    return bindPort;
  }

  public int getGroupPort() {
    return groupPort;
  }

  public String getVersion() {
    return version;
  }

  public String getBuild() {
    return build;
  }

  public long getStartTime() {
    return startTime;
  }

  @Override
  public String toString() {
    return "PlatformServer{" + "serverName=" + serverName + ", hostName=" + hostName + 
        ", hostAddress=" + hostAddress + ", bindAddress=" + bindAddress + ", bindPort=" + bindPort + 
        ", groupPort=" + groupPort + ", version=" + version + ", build=" + build + ", startTime=" + startTime + '}';
  }

  @Override
  public int hashCode() {
    return this.serverName.hashCode()
        ^ this.hostName.hashCode()
        ^ this.hostAddress.hashCode()
        ^ this.bindAddress.hashCode()
        ^ bindPort
        ^ groupPort
        ^ this.version.hashCode()
        ^ this.build.hashCode()
        ^ (int)startTime;
  }

  @Override
  public boolean equals(Object other) {
    boolean doesMatch = (this == other);
    if (!doesMatch && (null != other) && (getClass() == other.getClass()))
    {
      final PlatformServer that = (PlatformServer) other;
      doesMatch = this.serverName.equals(that.serverName)
          && this.hostName.equals(that.hostName)
          && this.hostAddress.equals(that.hostAddress)
          && this.bindAddress.equals(that.bindAddress)
          && (this.bindPort == that.bindPort)
          && (this.groupPort == that.groupPort)
          && this.version.equals(that.version)
          && this.build.equals(that.build)
          && (this.startTime == that.startTime);
    }
    return doesMatch;
  }
}
