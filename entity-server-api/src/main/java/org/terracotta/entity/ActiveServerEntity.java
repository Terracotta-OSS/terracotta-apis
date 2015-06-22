package org.terracotta.entity;

import java.util.Collections;


/**
 * The methods specifically supported by active entities.  As the active is responsible for "driving" much of the
 *  interaction, it has more capabilities than the corresponding passive.
 */
public interface ActiveServerEntity extends CommonServerEntity {
  /**
   * Get the concurrency strategy to be used for this server entity.
   *
   * @return concurrency strategy
   */
  ConcurrencyStrategy getConcurrencyStrategy();

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
   * Provide an iterable of payloads to be synced to a newly connected passive node.
   * 
   * @param concurrency - the concurrency key to be sync'd
   * @return - an iterable of payloads that will be serially sync'd to a new passive node.
   */
  default Iterable<byte[]> sync(int concurrency) {
    return Collections.emptyList();
  }
}
