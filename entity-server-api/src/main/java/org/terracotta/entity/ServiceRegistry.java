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
   * Get the unwrapped service instance of a given type.
   * @param configuration With which service should be provisioned
   * @param <K> Unwrapped platform resource
   * @param <S> Service type which wraps the Underlying platform resource
   * @param <SC> Service configuration type used by service provider
   * @return
   */
  <K, S extends Service<K>, SC extends ServiceConfiguration<S>> Optional<K> getService(SC configuration);

  /**
   * Destroy this ServiceRegistry and all its state.
   *
   * Destruction is cascaded down to sub-registries via Services. That is, when destroy is called on a service registry,
   * it will result in destroy() being called on each contained Service. Services will be expected to destroy created
   * sub-services.
   */
  void destroy();

  /**
   * Destroys a given service instance. This method takes care of passing information to service provider so clean up
   * at service provider can be performed.
   *
   *
   * @param service instance to be destroyed
   */
  void destroy(Service service);

}
