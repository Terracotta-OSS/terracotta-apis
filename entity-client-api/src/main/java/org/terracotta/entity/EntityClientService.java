package org.terracotta.entity;

import org.terracotta.connection.entity.Entity;
import org.terracotta.entity.EntityClientEndpoint;

import java.io.Serializable;

/**
 * @author twu
 */
public interface EntityClientService<T extends Entity, C> {
  /**
   * Check if this service handles the given entity type.
   *
   * @param cls type to check
   * @return true if this service does handle the given type
   */
  boolean handlesEntityType(Class<T> cls);

  /**
   * Serialize the configuration for this entity type out to a byte array
   * 
   * @param configuration configuration to be serialized
   * @return serialized config
   */
  byte[] serializeConfiguration(C configuration);

  /**
   * Deserialize a configuration from bytes
   *
   * @param configuration bytes to be deserialized
   * @return deserialized config
   */
  C deserializeConfiguration(byte[] configuration);
  
  /**
   * Create an entity of the given type.
   *
   * @param endpoint RPC endpoint for the entity
   * @return entity
   */
  T create(EntityClientEndpoint endpoint);
}
