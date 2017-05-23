/*
 *
 *  The contents of this file are subject to the Terracotta Public License Version
 *  2.0 (the "License"); You may not use this file except in compliance with the
 *  License. You may obtain a copy of the License at
 *
 *  http://terracotta.org/legal/terracotta-public-license.
 *
 *  Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 *  the specific language governing rights and limitations under the License.
 *
 *  The Covered Software is Entity API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.entity;

import java.util.Collection;

/**
 * <p>The registry given to entities so that they can look up the services hosted on the platform.</p>
 * 
 * <p>Note that the Service instances returned by this interface are not cached or interned, in any way (unless the
 *  underlying ServiceProvider decides to add that functionality).  This means that multiple requests for the same service,
 *  through the same registry, could return different instances.</p>
 * 
 * <p>ServiceProvider implementations see each call through a ServiceRegistry as originating from a specific consumerID,
 *  which is per-entity.  Using this mechanism is how a service knows which entity is contacting it, no matter how many
 *  instances of its underlying service were returned through repeated getService calls.  More information regarding
 *  consumerIDs can be found in {@link ServiceProvider}.</p>
 * 
 * <p>Note about using services together:  Services are not explicitly granted access to each other as there would be no
 *  consumerID to identify them to each other.  However, it is possible to use services together, so long as the entity and
 *  calling service know that they aren't going to collide in their usage of the underlying service they wish to access.
 *  This is because any service instance fetched through a given ServiceRegistry will have the same consumerID.  Therefore,
 *  it is possible to pass something fetched from one service, or even the entire ServiceRegistry, into another via its
 *  configuration or its public interface.  This is safe so long as the entity and calling service know that they are
 *  sharing access to the same underlying resource.</p>
 */
public interface ServiceRegistry {
  /**
   * <p>Get the service instance of a given serviceType subject to the constraints of the given configuration.</p>
   * 
   * <p>Note that successive calls to this method, even with the same configuration, usually return different instances to
   *  access the same underlying resource of the ServiceProvider.</p>
   * 
   * 
   * @param <T> type interface of the requested service
   * @param configuration With which service should be provisioned
   * @return an instance of service which will provide the requested interface
   * @throws ServiceException if multiple services satisfy the same configuration
   */
  <T> T getService(ServiceConfiguration<T> configuration) throws ServiceException;
  /**
   * <p>Get all the service instances of a given serviceType subject to the constraints of the given configuration.</p>
   * 
   * <p>Note that successive calls to this method, even with the same configuration, usually return different instances to
   *  access the same underlying resource of the ServiceProvider.</p>
   * 
   * @param <T> type interface of the requested service
   * @param configuration With which service should be provisioned
   * @return a collection of all the services that satisfy the given configuration
   */
  <T> Collection<T> getServices(ServiceConfiguration<T> configuration);
}
