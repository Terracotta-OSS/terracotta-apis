package org.terracotta.entity;

/**
 * @author twu
 */
public interface EntityClientEndpoint {

  byte[] getEntityConfiguration();

  void registerListener(EndpointListener listener);

  InvocationBuilder beginInvoke();
}
