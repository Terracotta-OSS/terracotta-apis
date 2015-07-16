package org.terracotta.entity;


/**
 * Service Provider which are provided at the platform level. Each of the service provisions its chunk of service by
 * in an appropriate configuration. The decision of namespacing a service is left upto the provider.
 *
 * @param <T> type interface of the service requested
 */
public interface ServiceProvider<T, C extends ServiceProviderConfiguration> {

  /**
   * The platform configuration based on which the Service provider can choose to initalize itself.
   *
   * @param configuration platform configuration
   */
  void initialize(C configuration);

  /**
   * Get an instance of service from the provider.
   *
   * @param configuration Service configuration which is to be used
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
   * Type of the service configuration to be used while configuring service provider.
   *
   * @return type of service provider configuration
   */

  Class<C> getServiceProviderConfigurationType();

}