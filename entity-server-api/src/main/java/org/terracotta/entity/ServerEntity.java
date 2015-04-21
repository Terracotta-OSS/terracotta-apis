package org.terracotta.entity;

import java.util.Optional;

/**
 * @author twu
 */
public interface ServerEntity {

  /**
   * Get the sharding strategy to be used for this server entity.
   *
   * @return shard strategy
   */
  ShardingStrategy getShardingStrategy();

  /**
   * Indicate that the given client is now connected up to this ServerEntity.
   *
   * @param clientID opaque identifier for the client
   */
  void connected(ClientID clientID);

  /**
   * Notify the ServerEntity that the given client has disconnected.
   *
   * @param clientID opaque client identifier
   */
  void disconnected(ClientID clientID);

  /**
   * Get configuration for given entity
   *
   * @return serialized byte array
   */
  byte[] getConfig();
  
  /**
   * Invoke a call on the given entity.
   *
   * @param clientID source from which the invocation originates.
   * @param arg entity specific invocation info
   * @return possible return value
   */
  byte[] invoke(ClientID clientID, byte[] arg);

  /**
   * Destroy all state associated with this entity
   */
  void destroy();
}
