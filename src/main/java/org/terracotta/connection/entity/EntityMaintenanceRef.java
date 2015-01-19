package org.terracotta.connection.entity;

/**
 * @author twu
 */
public interface EntityMaintenanceRef<T extends Entity> extends AutoCloseable {

  /**
   * Get the entity referred to by this mmode reference. Can return null if it doesn't exist.
   *
   * NOTE: this differs from the non-maintenance reference in that you don't need to release().
   * It's automatically broken by exiting maintenance mode.
   *
   * @return the entity
   */
  T getEntity();

  /**
   * Destroy the entity pointed to by this reference.
   */
  void destroy();

  /**
   * Create the entity with the given configuration
   *
   * @param configuration configuration to be applied to the entity
   */
  void create(EntityConfiguration configuration);

  /**
   * Release this maintenance mode reference.
   */
  void close();
}
