package org.terracotta.entity;

/**
 * Configuration which is used to configure the given service instance
 */
public interface ServiceConfiguration<T> {

  /**
   * Gets the type of service object which configuration is supposed to configure
   * @return type of service
   */
  Class<T> getServiceType();

}
