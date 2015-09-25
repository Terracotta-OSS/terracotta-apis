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

/**
 * Registry which Entities interact with list of services provided by the platform. Services provided to entity might
 * be namespaced or not, depending on  service provider.
 *
 * @author twu
 */
public interface ServiceRegistry {

  /**
   * Get the service instance of a given serviceType subject to the constraints of the given configuration.
   * 
   * @param <T> type interface of the requested service
   * @param configuration With which service should be provisioned
   * @return an instance of service which will provide the requested interface
   */
  <T> T getService(ServiceConfiguration<T> configuration);

}
