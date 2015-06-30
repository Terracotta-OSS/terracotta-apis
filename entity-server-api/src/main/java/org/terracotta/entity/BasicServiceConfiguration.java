/*
 * All content copyright (c) 2014 Terracotta, Inc., except as may otherwise
 * be noted in a separate copyright notice. All rights reserved.
 */
package org.terracotta.entity;

/**
 *
 * @author mscott
 */
public class BasicServiceConfiguration<T> implements ServiceConfiguration<T> {
  private final Class<T> type;
  public BasicServiceConfiguration(Class<T> clazz) {
    type = clazz;
  }

  @Override
  public Class<T> getServiceType() {
    return type;
  }
  
  
}
