package org.terracotta.entity;

import java.io.Serializable;

/**
 * @author twu
 */
public interface EntityServerService<T extends ServerEntity> {
  /**
   * Check if this service handles the given type
   *
   * @param typeName name of the type to check
   * @return true if this service works with the given type
   */
  boolean handlesEntityType(String typeName);

  /**
   * create an instance of the specified server entity
   *
   * @param registry registry of services provided by the server
   * @param configuration entity specific configuration object
   * @return server side entity
   */
  T createEntity(ServiceRegistry registry, Serializable configuration);

  /**
   * Get an existing entity
   *
   * TODO: Add some mechanism for the entity to deserialize its metadata out of object db?
   *
   * @param registry service registry provided by the server
   * @param configuration entity specific configuration
   * @return server side entity
   */
  T getEntity(ServiceRegistry registry, Serializable configuration);
}
