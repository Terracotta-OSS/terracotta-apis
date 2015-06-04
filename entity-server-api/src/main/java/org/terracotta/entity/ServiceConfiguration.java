package org.terracotta.entity;

import com.tc.object.EntityID;

/**
 * Configuration which is used to configure the given service instance
 */
public interface ServiceConfiguration<T extends Service> {

  /**
   * Gets the type of service object which configuration is supposed to configure
   * @return type of service
   */
  Class<? extends T> getServiceType();

}
