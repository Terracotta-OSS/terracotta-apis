package org.terracotta.entity;

import java.util.Collection;


/**
 * Service Provider which are provided at the platform level.  A service provider is responsible for instantiating the
 * services to interact with its underlying implementation in response to platform requests.  While the services are
 * generally used by entities, they are also used by the platform, itself.
 */
public interface ServiceProvider<T> {

  /**
   * The platform configuration based on which the Service provider can choose to initialize itself.
   *
   * @param configuration platform configuration
   * @return true if provider has successfully been initialized with the provided configuration
   */
  boolean initialize(ServiceProviderConfiguration configuration);

  /**
   * Get an instance of service from the provider.
   *
   * @param serviceType The type of service requested (should be one of getProvidedServiceTypes())
   * @param consumerID The unique ID used to name-space the returned service
   * @param configuration Service configuration which is to be used
   * @return service instance
   */
  <T> Service<T> getService(Class<T> serviceType, long consumerID, ServiceConfiguration<T> configuration);

  /**
   * Since a service provider can know how to build more than one type of service, this method allows the platform to query
   * the extent of that capability.  Returned is a collection of service types which can be returned by getService.
   *
   * @return A collection of the types of services which can be returned by the receiver.
   */
  Collection<Class<?>> getProvidedServiceTypes();

}
