package org.terracotta.entity;

import org.terracotta.connection.entity.EntityConfiguration;

/**
 * @author twu
 */
public interface EntityClientEndpoint {
  void setEntityConfiguration(EntityConfiguration entityConfiguration);

  EntityConfiguration getEntityConfiguration();

  String getTypeName();

  void setTypeName(String typeName);

  void registerListener(EndpointListener listener);

  InvocationBuilder beginInvoke();
}
