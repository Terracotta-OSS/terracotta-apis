package org.terracotta.connection.entity;

/**
 * @author twu
 */
public interface EntityMaintenanceRef<T extends Entity, C> extends AutoCloseable {
  /**
   * @return True if the underlying entity actually exists.
   */
  boolean doesExist();
  
  /**
   * Destroy the entity pointed to by this reference.
   */
  void destroy();

  /**
   * Create the entity with the given configuration
   *
   * @param configuration configuration to be applied to the entity
   */
  void create(C configuration);

  /**
   * Release this maintenance mode reference.
   */
  @Override
  void close();
}
