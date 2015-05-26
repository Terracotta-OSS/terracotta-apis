package org.terracotta.entity;


/**
 * The methods common to both active and passive entities.
 */
public interface CommonServerEntity {
  /**
   * Called when a client asks that an entity be explicitly created.
   */
  void createNew();
  
  /**
   * Called when starting a server from a persistent state and the entity is expected to already be known to the server.
   */
  void loadExisting();
  
  /**
   * Destroy all state associated with this entity.
   */
  void destroy();
}
