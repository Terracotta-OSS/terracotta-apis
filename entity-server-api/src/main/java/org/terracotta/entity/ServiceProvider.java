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

import java.io.Closeable;
import java.util.Collection;


/**
 * Service Provider which are provided at the platform level.  A service provider is responsible for instantiating the
 * services to interact with its underlying implementation in response to platform requests.  While the services are
 * generally used by entities, they are also used by the platform, itself.
 */
public interface ServiceProvider extends Closeable {

  /**
   * The platform configuration based on which the Service provider can choose to initialize itself.
   *
   * @param configuration platform configuration
   * @return true if provider has successfully been initialized with the provided configuration
   */
  boolean initialize(ServiceProviderConfiguration configuration);

  /**
   * Get an instance of service from the provider.
   *
   * @param consumerID The unique ID used to name-space the returned service
   * @param configuration Service configuration which is to be used
   * @return service instance
   */
  <T> T getService(long consumerID, ServiceConfiguration<T> configuration);

  /**
   * Since a service provider can know how to build more than one type of service, this method allows the platform to query
   * the extent of that capability.  Returned is a collection of service types which can be returned by getService.
   *
   * @return A collection of the types of services which can be returned by the receiver.
   */
  Collection<Class<?>> getProvidedServiceTypes();

  /**
   * Clears up state for this ServiceProvider including any persisted state
   *
   * Generally platform calls this method during platform initialization so there won't be any entities using
   * underlying services but ServiceProviders need to make sure that no entities (with the exception of platform whose consumerID is 0) using underlying services
   *
   * If there are any failures when clearing state, this method should inform Platform by throwing {@link ServiceProviderCleanupException}
   *
   * @throws ServiceProviderCleanupException if there are any failures
     */
  void clear() throws ServiceProviderCleanupException;

}
