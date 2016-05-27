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

import com.tc.classloader.CommonComponent;


/**
 * The common interface for a built-in service which provides the ability for an entity to asynchronously message itself.
 * This is also the underlying mechanism which allows entities to contact each other (as they can safely expose code which
 * doesn't touch their state, but calls through to this interface, thus allowing a future invocation).
 * This is because an entity can't safely call into another one since its execution state is undefined, relative to another
 * entity, and any action taken by such a cross-entity call would not be replicated to passive entities.
 */
@CommonComponent
public interface IEntityMessenger {
  /**
   * Asynchronously send a message to the entity instance which looked up the service instance.
   * 
   * @param message The message to send.
   * @throws MessageCodecException The message could not be serialized.
   */
  void messageSelf(EntityMessage message) throws MessageCodecException;

  /**
   * Asynchronously send a message to the entity instance which looked up the service instance but also blocks the final
   * retirement acknowledgement of originalMessageToDefer until newMessageToSchedule completes.
   * 
   * @param originalMessageToDefer The currently executing message whose retirement must be deferred.
   * @param newMessageToSchedule The new message to send.
   * @throws MessageCodecException The message could not be serialized.
   */
  void messageSelfAndDeferRetirement(EntityMessage originalMessageToDefer, EntityMessage newMessageToSchedule) throws MessageCodecException;
}
