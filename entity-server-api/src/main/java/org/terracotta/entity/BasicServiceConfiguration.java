/*
 * Copyright Terracotta, Inc.
 * Copyright Super iPaaS Integration LLC, an IBM Company 2024
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
