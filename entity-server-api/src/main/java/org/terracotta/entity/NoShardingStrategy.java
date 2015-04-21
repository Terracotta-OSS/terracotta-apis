package org.terracotta.entity;

/**
 * Implements the simplest sharding strategy possible: just don't shard.
 *
 * @author twu
 */
public class NoShardingStrategy implements ShardingStrategy {
  @Override
  public int shardKey(byte[] payload) {
    return 0;
  }
}
