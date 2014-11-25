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
   * TODO: add access to the server services
   *
   * @param configuration entity specific configuration object
   * @return server entity
   */
  T createEntity(Serializable configuration);
}
