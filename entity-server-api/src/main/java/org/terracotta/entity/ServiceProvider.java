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
 * <p>A service provider is responsible for instantiating the services to interact with its underlying implementation in
 *  response to platform requests.  While the services are generally used by entities, they are also used by the platform,
 *  itself.</p>
 * 
 * <p>It is possible for a given ServiceProvider implementation to expose multiple different service types, as returned by
 *  {@link #getProvidedServiceTypes()}.  When an entity requests a service instance, the ServiceConfiguration it provides is
 *  used to determine which ServiceProvider instances to check.</p>
 * <p>NOTE:  Currently, it is a failure for more than one ServiceProvider to produce a service instance for the same
 *  ServiceConfiguration.  It is considered a fatal server misconfiguration due to the ambiguity.</p>
 * 
 * <p>Note that there are no threading restrictions on how the methods in this interface will be called.  This means that
 *  any implementation which has shared state must ensure that it can manage this state in a thread safe way (subject to
 *  whatever thread safety its own state requires).</p>
 */
public interface ServiceProvider extends StateDumpable {
  /**
   * <p>PLATFORM_CONSUMER_ID is the consumerID used by the platform, itself, when it wants to interact with services.
   *  Normal entity instances use positive consumerID values.</p>
   */
  public final long PLATFORM_CONSUMER_ID = 0; 

  /**
   * <p>The platform configuration based on which the receiver can choose to initialize itself.</p>
   * <p>This is called once on each provider instance, at server start-up time, before any other methods have been called on
   *  the instance.</p>
   * <p>Note that it is possible for there to be multiple instances of the same ServiceProvider if the server had multiple
   *  configuration stanzas handled by the same ServiceProvider class.  As the entities requesting services do not have
   *  direct access to the set of initialized ServiceProviders, they do not require special accounting for this possibility.
   *  The entities can still only select a ServiceProvider via a matching ServiceConfiguration passed to
   *  {@link #getService(long, ServiceConfiguration<T>)}.</p>
   * 
   * @param configuration The configuration for this ServiceProvider instance.
   * @param platformConfiguration platform configuration
   * @return True if provider has successfully been initialized with the provided configuration, false if this instance
   *  shouldn't be registered for the given configuration (it will be discarded).
   */
  boolean initialize(ServiceProviderConfiguration configuration, PlatformConfiguration platformConfiguration);

  /**
   * <p>Get an instance of a service from the provider.</p>
   * <p>Note that this method can be called any number of times, over the life of a ServiceProvider instance, potentially
   *  concurrently or multiple times for the same consumerID.  If the implementation wishes to intern, or otherwise track,
   *  the instances it returns, it will need to ensure its approach is robust against concurrent calls.</p>
   * <p>Note that the consumerID is a unique number for each entity instance which is used to effectively name-space the
   *  service instances under the entities.  An entity instance will have the same consumerID for its entire life (including
   *  after restart or fail-over).  The value is always non-negative.</p>
   * <p>The receiver is expected to return null if it is not able to create a service with the given
   *  ServiceConfiguration.</p>
   * 
   * @param consumerID The unique, non-negative, ID used to name-space the returned service for a specific consuming entity
   *  (or the platform).
   * @param configuration Service configuration which is to be used.
   * @return The service instance (which may be either new or a cached/interned instance for this consumerID) or null, if
   *  the receiver cannot create a service for the given ServiceConfiguration.
   */
  <T> T getService(long consumerID, ServiceConfiguration<T> configuration);

  /**
   * <p>Since a service provider can know how to build more than one type of service, this method allows the platform to
   *  query the extent of that capability.  Returned is a collection of service types which can be returned by future
   *  getService calls.</p>
   *
   * @return A collection of the types of services which can be returned by the receiver.
   */
  Collection<Class<?>> getProvidedServiceTypes();

  /**
   * <p>Clears up state for this ServiceProvider including any persisted state.</p>
   * <p>This is called, by the platform, when it wishes to explicitly clear the server's state (both in-memory and
   *  persistent).  This method should leave the underlying state clean such that any client entities wishing to re-sync state
   *  can start fresh as if the service are started fresh for the first time.</p>
   * <p>Generally, the platform calls this method during platform initialization so there won't be any entities using the
   *  underlying service instances.  In a more concrete sense, no service instances created before this call are considered
   *  valid after the call returns.</p>
   * <p>In terms of the specific place in the life-cycle, relative to other calls in this interface, it is always called
   *  after initialize and will only ever be called in the cases where the server is about to be brought down so it can
   *  restart in a fully clean state before being freshly synchronized with the active.</p>
   * <p>If there are any failures when clearing state, this method should inform Platform by throwing
   *  {@link ServiceProviderCleanupException}.</p>
   *
   * @throws ServiceProviderCleanupException Describes any failures in the attempt to clear state.
   */
  void prepareForSynchronization() throws ServiceProviderCleanupException;

  @Override
  default void addStateTo(StateDumpCollector stateDumpCollector) {
    stateDumpCollector.addState(this.getClass().getName(), this.toString());
  }
}
