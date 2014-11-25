package org.terracotta.entity;

import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityConfiguration;
import org.terracotta.entity.EntityClientEndpoint;

/**
 * @author twu
 */
public interface EntityClientService<T extends Entity> {
  /**
   * Check if this service handles the given entity type.
   *
   * @param cls type to check
   * @return true if this service does handle the given type
   */
  boolean handlesEntityType(Class<T> cls);

  /**
   * Create an entity of the given type.
   *
   * @param endpoint RPC endpoint for the entity
   * @param configuration entity specific configuration
   * @return entity
   */
  T create(final EntityClientEndpoint endpoint, EntityConfiguration configuration);
}
