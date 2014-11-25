package org.terracotta.entity;

import com.tc.io.TCSerializable;

/**
 * @author twu
 */
public interface ServerEntity {
  /**
   * Invoke a call on the given entity.
   *
   * @param arg entity specific invocation info
   * @return possible return value
   */
  byte[] invoke(byte[] arg);
}
