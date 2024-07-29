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
 * The interface which describes the abstraction over the channel which sends message payloads to the passive version of an
 * entity, on a passive server in the stripe.
 * Note that the instance is always specific to a entity and concurrency key and also can't be held beyond the scope of the
 * method where it is provided. The hashcode()/equals() contract should be implemented in
 * terms of the underlying passives it is talking to, and the concurrency it is related to
 *
 */
public interface PassiveSynchronizationChannel<M extends EntityMessage> {
  /**
   * Requests that a message payload be sent to the passive for this entity on this concurrency key.
   * This payload will be deserialized by MessageCodec, on the passive (the concurrency key will be provided so it
   * need not be part of the payload).
   * 
   * @param message The entity-specific EntityMessage to send.
   */
  public void synchronizeToPassive(M message);
}
