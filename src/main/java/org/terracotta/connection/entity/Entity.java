package org.terracotta.connection.entity;

/**
 * @author twu
 */
public interface Entity extends AutoCloseable {

  /**
   * Release this handle on the entity. The instance will be unusable after this call.
   */
  void close();
}
