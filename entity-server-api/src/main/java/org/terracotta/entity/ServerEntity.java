package org.terracotta.entity;

/**
 * @author twu
 */
public interface ServerEntity {

  /**
   * Get configuration for given entity
   *
   * @return serialized byte array
   */
  byte[] getConfig();
  
  /**
   * Invoke a call on the given entity.
   *
   * @param arg entity specific invocation info
   * @return possible return value
   */
  byte[] invoke(byte[] arg);

  /**
   * Destroy all state associated with this entity
   */
  void destroy();
}
