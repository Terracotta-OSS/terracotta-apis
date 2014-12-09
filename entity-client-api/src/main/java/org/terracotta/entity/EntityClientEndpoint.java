package org.terracotta.entity;

import java.io.Serializable;

/**
 * @author twu
 */
public interface EntityClientEndpoint {
  void setEntityConfiguration(Serializable entityConfiguration);

  Serializable getEntityConfiguration();

  void registerListener(EndpointListener listener);

  InvocationBuilder beginInvoke();
}
