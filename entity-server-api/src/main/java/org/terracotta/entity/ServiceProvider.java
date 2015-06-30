package org.terracotta.entity;

import org.terracotta.config.TcConfiguration;

/**
 * Service Provider which are provided at the platform level. Each of the service provisions its chunk of service by
 * in an appropriate configuration. The decision of namespacing a service is left upto the provider.
 *
 */
public interface ServiceProvider<T> {

  /**
   * The platform configuration based on which the Service provider can choose to initalize itself.
   *
   * @param configuration platform configuration
   */
  void initialize(TcConfiguration configuration);

  /**
   * Get an instance of service from the provider.
   *
   * @param configuration Service configuratino which is to be used
   * @return service instance
   */
  Service<T> getService(ServiceConfiguration<T> configuration);

  /**
   * Type of service which is provided by the service provider
   *
   * @return type of service
   */
  Class<T> getServiceType();

  /**
   * Destroys a given service instance.
   *
   * @param service instance to be destroyed
   */
  void destroy(Service<T> service);
}