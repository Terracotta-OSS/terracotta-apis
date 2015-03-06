package org.terracotta.connection;

import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityMaintenanceRef;
import org.terracotta.connection.entity.EntityRef;

import java.util.Collection;

/**
 * Represents a connection to a cluster
 *
 * @author twu
 */
public interface Connection extends AutoCloseable {
  /**
   * Get a reference to the given entity. The entity may or may not yet exist. This is a pointer to the spot where the
   * entity would be if it did exist.
   *
   * @param cls entity class
   * @param name name of the entity
   * @param <T> entity type
   * @return reference to the entity
   */
  <T extends Entity, C> EntityRef<T, C> getEntityRef(Class<T> cls, String name);

  /**
   * Grab a maintenance mode reference to the specified entity of given type. This reference grants exclusive access.
   * Attempting to get a maintenance mode reference while outstanding holds exist will cause maintenance mode reference
   * acquisition to block.
   *
   * @param cls entity class
   * @param name name of the entity
   * @param <T> entity type
   * @return exclusive reference to the entity
   */
  <T extends Entity, C> EntityMaintenanceRef<T, C> acquireMaintenanceModeRef(Class<T> cls, String name);

  /**
   * Get references to all the existing entities of the given type.
   *
   * Note: it may be possible that the references will be
   * dead by the time they are examined.
   *
   * @param cls entity class
   * @param <T> entity type
   * @return collection of entity references that were live at the time of the call.
   */
  <T extends Entity, C> Collection<EntityRef<T, C>> getEntityRefsOfType(Class<T> cls);
}
