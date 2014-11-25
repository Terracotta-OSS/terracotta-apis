package org.terracotta.entity;

import org.terracotta.connection.entity.EntityConfiguration;

import java.io.Serializable;

/**
 * @author twu
 */
public interface EntityClientEndpoint {
  void setEntityConfiguration(Serializable entityConfiguration);

  Serializable getEntityConfiguration();

  String getTypeName();

  void setTypeName(String typeName);

  void registerListener(EndpointListener listener);

  InvocationBuilder beginInvoke();
}
