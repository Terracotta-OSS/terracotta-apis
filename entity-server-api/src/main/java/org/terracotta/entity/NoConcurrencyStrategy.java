package org.terracotta.entity;

/**
 * Implements the simplest concurrency strategy possible: use the same key for everything to force serial execution
 * in the same queue.
 *
 * @author twu
 */
public class NoConcurrencyStrategy implements ConcurrencyStrategy {
  
  public NoConcurrencyStrategy() {
  }

  @Override
  public int concurrencyKey(byte[] payload) {
    return ConcurrencyStrategy.MANAGEMENT_KEY;
  }
}
