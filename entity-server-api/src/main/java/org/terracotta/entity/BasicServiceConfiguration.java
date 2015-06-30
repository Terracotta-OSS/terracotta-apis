/*
 * All content copyright (c) 2014 Terracotta, Inc., except as may otherwise
 * be noted in a separate copyright notice. All rights reserved.
 */
package org.terracotta.entity;

/**
 *
 * A service configuration that only provides the interface class of the service desired.
 * It will be up to the entity to manage the lifecycle of the service through the
 * service class and no naming is provided
 */
public class BasicServiceConfiguration<T> implements ServiceConfiguration<T> {
  private final Class<T> type;
  public BasicServiceConfiguration(Class<T> clazz) {
    type = clazz;
  }
/**
 * 
 * @return type of service interface requested
 */
  @Override
  public Class<T> getServiceType() {
    return type;
  }
  
  
}
