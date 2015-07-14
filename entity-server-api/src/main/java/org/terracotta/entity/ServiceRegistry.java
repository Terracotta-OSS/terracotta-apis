package org.terracotta.entity;

import java.util.Optional;

/**
 * Registry which Entities interact with list of services provided by the platform. Services provided to entity might
 * be namespaced or not, depending on  service provider.
 *
 * @author twu
 */
public interface ServiceRegistry {

  /**
   * Get the service instance of a given serviceType subject to the constraints of the given configuration.
   * 
   * @param <T> type interface of the requested service
   * @param serviceType The class of the underlying service to request
   * @param configuration With which service should be provisioned
   * @return an instance of service which will provide the requested interface
   */
  <T> Optional<Service<T>> getService(Class<T> serviceType, ServiceConfiguration<T> configuration);

  /**
   * Destroy this ServiceRegistry and all its state.
   *
   * Destruction is cascaded down to sub-registries via Services. That is, when destroy is called on a service registry,
   * it will result in destroy() being called on each contained Service. Services will be expected to destroy created
   * sub-services.
   */
  void destroy();

}
