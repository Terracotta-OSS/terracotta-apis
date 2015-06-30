package org.terracotta.entity;

/**
 * Configuration which is used to configure the given service instance
 * @param <T> type interface of the service being configured
 */
public interface ServiceConfiguration<T> {

  /**
   * Gets the type of service object which configuration is supposed to configure
   * @return type interface of service
   */
  Class<T> getServiceType();

}
