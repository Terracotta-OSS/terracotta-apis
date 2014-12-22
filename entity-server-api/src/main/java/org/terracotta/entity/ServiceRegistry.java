package org.terracotta.entity;

/**
 * @author twu
 */
public interface ServiceRegistry {

  /**
   * Get the unwrapped service instance of a given type.
   *
   * TODO: make Optional to handle case when the service doesn't exist?
   *
   * @param type type of the service to get
   * @param <T> type of service
   * @return instance of the service
   */
  <T> T getService(Class<T> type);

}
