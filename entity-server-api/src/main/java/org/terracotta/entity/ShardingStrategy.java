package org.terracotta.entity;

/**
 * @author twu
 */
public interface ShardingStrategy {
  /**
   * Given a payload for an entity request, return an integer shard key. The server will guarantee that requests with
   * the same shard key will run on the same thread. It does not, however, guarantee that requests with different
   * shard keys will run on different threads (i.e. it's perfectly valid for the server to ignore the shard key and
   * put everything on a single thread).
   *
   * At a minimum this method needs to be thread-safe. Ideally, it should be a pure function.
   *
   * @param payload request payload passed in from a client
   * @return integer shard key
   */
  int shardKey(byte[] payload);
}
