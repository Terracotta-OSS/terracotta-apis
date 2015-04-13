package org.terracotta.entity;

import java.lang.Object;

/**
 * @author twu
 */
public interface Service<T> {

  /**
   * The type of service provided by this instance
   *
   * @return service type
   */
  Class<? extends T> getServiceType();

  /**
   * Initialize the service
   *
   * TODO: Add configuration
   */
  void initialize(Object configuration);

  /**
   * Get the service
   *
   * @return unwrapped instance of the service
   */
  T get();

  /**
   * Get a sandboxed subservice of the current service with the given name.
   *  
   * Ideally, this should return a sandboxed view of the service. However it is ultimately up to services to 
   * decide what is the best way to sandbox.
   *
   * @param name name of the sub-service
   * @return sub service
   */
  Service<T> subService(String name);
}
