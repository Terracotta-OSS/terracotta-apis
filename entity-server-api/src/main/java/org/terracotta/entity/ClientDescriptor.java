/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 */
package org.terracotta.entity;

import com.tc.object.ClientInstanceID;

/**
 * An opaque token representing a specific entity instance on a specific client node.
 * This is used by server-side code to specifically communicate with or track connection status of a specific client-side
 * object instance.
 */
public class ClientDescriptor {
  private final ClientID clientID;
  private final ClientInstanceID clientInstanceID;
  
  public ClientDescriptor(ClientID clientID, ClientInstanceID clientInstanceID) {
    this.clientID = clientID;
    this.clientInstanceID = clientInstanceID;
  }
  
  public ClientID getClientID() {
    return this.clientID;
  }
  
  public ClientInstanceID getClientInstanceID() {
    return this.clientInstanceID;
  }
  
  @Override
  public int hashCode() {
    return this.clientID.hashCode() ^ this.clientInstanceID.hashCode();
  }
  
  @Override
  public boolean equals(Object other) {
    boolean doesMatch = (this == other);
    if (!doesMatch && (getClass() == other.getClass()))
    {
      final ClientDescriptor that = (ClientDescriptor) other;
      doesMatch = this.clientID.equals(that.clientID)
          && this.clientInstanceID.equals(that.clientInstanceID);
    }
    return doesMatch;
  }
}
