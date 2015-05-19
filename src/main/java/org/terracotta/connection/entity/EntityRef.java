package org.terracotta.connection.entity;

/**
 * @author twu
 */
public interface EntityRef<T extends Entity, C> {
  /**
   * Gets the entity pointed to by this reference. Can return null if no entity exists
   *
   * @return entity
   */
  T fetchEntity();

  /**
   * Gets the name of the entity
   *
   * @return name
   */
  String getName();
}
