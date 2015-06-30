package org.terracotta.entity;

/**
 * @author twu
 * @param <T> the type interface of this service
 */
public interface Service<T> {

  /**
   * Initialize the service
   *
   * @param configuration type of configuration which is used to initialize the configuration
   */
  void initialize(ServiceConfiguration<? extends T> configuration);

  /**
   * Get the service
   *
   * @return unwrapped instance of the service
   */
  T get();


  /**
   * Destroy the state of this service.
   *
   * This is to be invoked from the service registry level not to be invoked by entity directly.
   */
  void destroy();
}
