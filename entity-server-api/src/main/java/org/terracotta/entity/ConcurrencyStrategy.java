package org.terracotta.entity;

/**
 * The concurrency strategy is used to manage and control this entity.  
 */
public interface ConcurrencyStrategy {
  /**
   * UNIVERSAL_KEY is a negative key that indicates a request requires no order and can be run concurrently with any
   * other entity operation
   */
  public final int UNIVERSAL_KEY = Integer.MIN_VALUE; 
  /**
   * MANAGEMENT_KEY is a zero key which is used to manage the entity (anything other than invoke action).
   */
  public final int MANAGEMENT_KEY = 0; 
  /**
   * Given a payload for an entity request, return an integer key. The server will guarantee that requests with
   * the same key greater than or equal to zero will run linearly. It does not, however, guarantee that requests 
   * with different keys will run on different threads (i.e. it's perfectly valid for the server to ignore the key and
   * put everything on a single thread).
   * 
   * Any negative key is considered invalid and can be executed concurrently with any other entity request
   *
   * The zero key is used for management of the entity.  If zero is returned as the key, execution will run serially to 
   * platform management of the entity.
   * 
   * At a minimum this method needs to be thread-safe. Ideally, it should be a pure function.
   *
   * @param payload request payload passed in from a client
   * @return integer key 
   */
  int concurrencyKey(byte[] payload);
}
