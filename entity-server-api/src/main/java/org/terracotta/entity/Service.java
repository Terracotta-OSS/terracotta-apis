package org.terracotta.entity;

/**
 * @author twu
 */
public interface Service<T> {

  /**
   * The type of service provided by this instance
   *
   * @return service type
   */
  Class<T> getServiceType();

  /**
   * Initialize the service
   *
   * TODO: Add configuration
   */
  void initialize();

  /**
   * Get the service
   *
   * @return unwrapped instance of the service
   */
  T get();
}
