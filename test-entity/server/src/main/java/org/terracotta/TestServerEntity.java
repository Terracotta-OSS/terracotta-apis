package org.terracotta;

import org.terracotta.entity.ServerEntity;

/**
 * @author twu
 */
public class TestServerEntity implements ServerEntity {
  @Override
  public byte[] invoke(byte[] arg) {
    return arg;
  }

  @Override
  public void destroy() {
  }
}
