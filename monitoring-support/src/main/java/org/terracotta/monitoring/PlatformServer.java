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
 *
 */
@CommonComponent
public class PlatformServer {
  private final String serverName;
  private final String hostName;
  private final String hostAddress;
  private final String bindAddress;
  private final int bindPort;
  private final int groupPort;
  private final String version;
  private final String build;
  private final long startTime;

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

}
