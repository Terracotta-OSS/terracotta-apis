package org.terracotta.entity;


/**
 * The methods specifically supported by passive entities.  Note that a passive doesn't know anything about
 *  connected clients, nor can it return data to them.
 */
public interface PassiveServerEntity extends CommonServerEntity {
  /**
   * Invoke a call on the given entity.  Note that passive entities can't return data to the client.
   *
   * @param arg entity specific invocation info
   */
  void invoke(byte[] arg);
}
