package org.terracotta.entity;


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
   * @param clientDescriptor client-side instance which connected
   */
  void connected(ClientDescriptor clientDescriptor);

  /**
   * Notify the ServerEntity that the given client has disconnected.
   *
   * @param clientDescriptor client-side instance which disconnected
   */
  void disconnected(ClientDescriptor clientDescriptor);

  /**
   * Get configuration for given entity
   *
   * @return serialized byte array
   */
  byte[] getConfig();
  
  /**
   * Invoke a call on the given entity.
   *
   * @param clientDescriptor source instance from which the invocation originates.
   * @param arg entity specific invocation info
   * @return possible return value
   */
  byte[] invoke(ClientDescriptor clientDescriptor, byte[] arg);

  /**
   * Destroy all state associated with this entity
   */
  void destroy();
}
