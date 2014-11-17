package org.terracotta.connection.entity;

/**
 * @author twu
 */
public interface EntityRef<T extends Entity> {
  /**
   * Gets the entity pointed to by this reference. Can return null if no entity exists
   *
   * @return entity
   */
  T acquireEntity();

  /**
   * Gets the entity with the specified configuration. If the entity does not yet exist, return null. If the entity exists
   * with a matching configuration return it. If the entity exists with a non-matching configuration throw an exception.
   *
   * @param configuration configuration to check against
   * @return entity if it exists and matches the configuration
   * @throws ConfigurationMismatchException thrown if an entity exists with a conflicting configuration.
   */
  T acquireEntity(EntityConfiguration configuration) throws ConfigurationMismatchException;

  /**
   * Gets the name of the entity
   *
   * @return name
   */
  String getName();
}
