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
 * The methods common to both active and passive entities.
 * @param <M> The high-level message type used by the active and passive server-side entities.  This type is created by the
 *  implementation's MessageCodec and also read by the ConcurrencyStrategy.
 * @param <R> The high-level message type returned by invoke calls on the active entity.
 */
public interface CommonServerEntity<M extends EntityMessage, R extends EntityResponse> extends StateDumpable {
  /**
   * <p>Called when a client asks that an entity be explicitly created.  Also called at the very beginning of passive sync of
   *  the entity, asking it to be created for the first time.</p>
   * <p>This is distinct from cases such as restart or fail-over when the object will receive a loadExisting() call,
   *  instead.</p>
   * <p>Note that this call is made on the {@link ConcurrencyStrategy#MANAGEMENT_KEY}, meaning that it is serialized with
   *  respect to all other messages enqueued for the entity.</p>
   * @throws ConfigurationException If an instance cannot be created as new with the configuration it was given.
   */
  void createNew() throws ConfigurationException;
  
  /**
   * <p>Destroy all state associated with this entity.</p>
   * <p>This is called in response to a client explicitly requesting that the entity be destroyed.  Other situations where
   *  the object representing the entity may be discarded (server goes down for a restart or the server is being promoted to
   *  active) will not result in this call.</p>
   * <p>Note that this call is made on the {@link ConcurrencyStrategy#MANAGEMENT_KEY}, meaning that it is serialized with
   *  respect to all other messages enqueued for the entity.</p>
   */
  void destroy();

  @Override
  default void addStateTo(StateDumpCollector stateDumpCollector) {
    stateDumpCollector.addState(this.getClass().getName(), this.toString());
  }

  /**
   * Notify an entity that a source id is gone.
   * @param sourceId
   */
  default void notifyDestroyed(ClientSourceId sourceId) {
    
  }
}
