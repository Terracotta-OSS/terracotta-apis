package org.terracotta.entity;

/**
 * @author twu
 */
public interface ServerEntity {

  /**
   * Indicate that the given sourceID (usually a client) is now connected up to this ServerEntity.
   *
   * @param sourceID opaque identifier for the source
   */
  void connected(SourceID sourceID);

  /**
   * Notify the ServerEntity that the given source has disconnected.
   *
   * @param sourceID opaque source identifier
   */
  void disconnected(SourceID sourceID);

  /**
   * Get configuration for given entity
   *
   * @return serialized byte array
   */
  byte[] getConfig();
  
  /**
   * Invoke a call on the given entity.
   *
   * @param sourceID source from which the invocation originates.
   * @param arg entity specific invocation info
   * @return possible return value
   */
  byte[] invoke(SourceID sourceID, byte[] arg);

  /**
   * Destroy all state associated with this entity
   */
  void destroy();
}
