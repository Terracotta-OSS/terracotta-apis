package org.terracotta;

import org.terracotta.entity.NoShardingStrategy;
import org.terracotta.entity.ServerEntity;
import org.terracotta.entity.ClientID;
import org.terracotta.entity.ShardingStrategy;

/**
 * @author twu
 */
public class TestServerEntity implements ServerEntity {
  @Override
  public ShardingStrategy getShardingStrategy() {
    return new NoShardingStrategy();
  }

  @Override
  public void connected(ClientID clientID) {
  }

  @Override
  public void disconnected(ClientID clientID) {
  }

  @Override
  public byte[] getConfig() {
    return new byte[0];
  }

  @Override
  public byte[] invoke(ClientID clientID, byte[] arg) {
    return arg;
  }

  @Override
  public void destroy() {
  }
}
