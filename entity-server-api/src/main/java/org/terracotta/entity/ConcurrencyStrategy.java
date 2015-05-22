package org.terracotta.entity;

/**
 * @author twu
 */
public interface ConcurrencyStrategy {
  /**
   * Given a payload for an entity request, return an integer key. The server will guarantee that requests with
   * the same key will run on the same thread. It does not, however, guarantee that requests with different
   * keys will run on different threads (i.e. it's perfectly valid for the server to ignore the key and
   * put everything on a single thread).
   *
   * At a minimum this method needs to be thread-safe. Ideally, it should be a pure function.
   *
   * @param payload request payload passed in from a client
   * @return integer key
   */
  int shardKey(byte[] payload);
}
