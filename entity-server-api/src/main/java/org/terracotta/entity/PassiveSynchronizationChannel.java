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
 * The interface which describes the abstraction over the channel which sends message payloads to the passive version of an
 * entity, on a passive server in the stripe.
 * Note that the instance is always specific to a entity and concurrency key and also can't be held beyond the scope of the
 * method where it is provided.
 */
public interface PassiveSynchronizationChannel {
  /**
   * Requests that a message payload be sent to the passive for this entity on this concurrency key.
   * This payload will be deserialized by MessageDeserializer, on the passive (the concurrency key will be provided so it
   * need not be part of the payload).
   * 
   * @param payload The byte[]-encoding of a message.
   */
  public void synchronizeToPassive(byte[] payload);
}
