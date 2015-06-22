package org.terracotta.entity;

import java.util.Collections;
import java.util.Iterator;

/**
 * Implements the simplest concurrency strategy possible: use the same key for everything to force serial execution
 * in the same queue.
 *
 * @author twu
 */
public class NoConcurrencyStrategy implements ConcurrencyStrategy {
  
  private final int concurrency;

  public NoConcurrencyStrategy() {
    concurrency = 0;
  }
  
  public NoConcurrencyStrategy(int set) {
    concurrency = set;
  }
  
  @Override
  public int concurrencyKey(byte[] payload) {
    return concurrency;
  }

  @Override
  public Iterator<Integer> iterator() {
    return Collections.singleton(concurrency).iterator();
  }
}
