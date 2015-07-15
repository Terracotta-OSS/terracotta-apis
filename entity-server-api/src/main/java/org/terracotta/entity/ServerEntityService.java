package org.terracotta.entity;


public interface ServerEntityService<ID, A extends ActiveServerEntity, P extends PassiveServerEntity> {
  /**
   * Used for ensuring that the entity version implementation version is consistent between server and client,
   * active and passive, and pre-restart and post-restart.
   * There is no support for partially-compatible versions.  If there is a mismatch here, it is an absolute
   * incompatibility.
   * Note that the version is expected to be a positive integer (> 0).
   * 
   * @return The version of the entity's implementation.
   */
  long getVersion();
  
  /**
   * Check if this service handles the given type
   *
   * @param typeName name of the type to check
   * @return true if this service works with the given type
   */
  boolean handlesEntityType(String typeName);

  /**
   * Create an instance of the specified server entity in active mode.
   *
   * @param id identifier for a given entity
   * @param registry registry of services provided by the server
   * @param configuration entity specific configuration object
   * @return server side entity
   */
  A createActiveEntity(ID id, ServiceRegistry registry, byte[] configuration);

  /**
   * Create an instance of the specified server entity in passive mode.
   *
   * @param id identifier for a given entity
   * @param registry registry of services provided by the server
   * @param configuration entity specific configuration object
   * @return server side entity
   */
  P createPassiveEntity(ID id, ServiceRegistry registry, byte[] configuration);
}
