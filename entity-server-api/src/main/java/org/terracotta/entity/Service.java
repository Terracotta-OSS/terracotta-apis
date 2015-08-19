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
 * @author twu
 * @param <T> the type interface of this service
 */
public interface Service<T> {

  /**
   * Initialize the service
   *
   * @param configuration type of configuration which is used to initialize the configuration
   */
  void initialize(ServiceConfiguration<? extends T> configuration);

  /**
   * Get the service
   *
   * @return unwrapped instance of the service
   */
  T get();


  /**
   * Destroy the state of this service.
   *
   * This is to be invoked from the service registry level not to be invoked by entity directly.
   */
  void destroy();
}
