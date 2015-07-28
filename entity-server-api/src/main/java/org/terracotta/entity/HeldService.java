/*
 * All content copyright (c) 2014 Terracotta, Inc., except as may otherwise
 * be noted in a separate copyright notice. All rights reserved.
 */
package org.terracotta.entity;

/**
 *
 * @author mscott
 * @param <T> type of the held service
 */
public class HeldService<T> implements Service<T> {
  
  private final T held;

  public HeldService(T held) {
    this.held = held;
  }

  @Override
  public void initialize(ServiceConfiguration<? extends T> configuration) {
// do nothing
  }

  @Override
  public T get() {
    return held;
  }

  @Override
  public void destroy() {
// do nothing
  }
    
}
