package org.terracotta;

import org.terracotta.entity.ServerEntity;
import org.terracotta.entity.SourceID;

/**
 * @author twu
 */
public class TestServerEntity implements ServerEntity {
  @Override
  public void connected(SourceID sourceID) {
  }

  @Override
  public void disconnected(SourceID sourceID) {
  }

  @Override
  public byte[] getConfig() {
    return new byte[0];
  }

  @Override
  public byte[] invoke(SourceID sourceID, byte[] arg) {
    return arg;
  }

  @Override
  public void destroy() {
  }
}
