package org.terracotta.entity;

import java.util.Optional;

/**
 * @author twu
 */
public interface ServiceRegistry {

  /**
   * Get the unwrapped service instance of a given type.
   *
   * @param type type of the service to get
   * @param <T> type of service
   * @return instance of the service if it exists
   */
  <T> Optional<T> getService(Class<T> type);

  /**
   * Get a sandboxed sub registry below the current one.
   *
   * @param name name of the subregistry to get
   * @return sub registry
   */
  ServiceRegistry subRegistry(String name);

  /**
   * Destroy this ServiceRegistry and all its state.
   *
   * Destruction is cascaded down to sub-registries via Services. That is, when destroy is called on a service registry,
   * it will result in destroy() being called on each contained Service. Services will be expected to destroy created
   * sub-services.
   */
  void destroy();
}
