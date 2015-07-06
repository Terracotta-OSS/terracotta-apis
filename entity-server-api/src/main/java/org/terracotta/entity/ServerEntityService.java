package org.terracotta.entity;


/**
 * @author twu
 */
public interface ServerEntityService<ID, A extends ActiveServerEntity, P extends PassiveServerEntity> {
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
