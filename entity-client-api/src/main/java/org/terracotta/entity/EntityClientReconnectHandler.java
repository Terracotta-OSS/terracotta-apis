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
 * The interface which must be implemented by any object wishing to handle the end-point reconnect to provide additional
 * extended reconnect data.
 * Typically, this is the entity which exists on top of the end-point.
 * 
 * Note that this interface is called on the thread handling the reconnect, not the thread running the user code.  This means
 * that is it possible for the reconnect data request to run concurrently with the user code which might change its meaning.
 * Implementors must be aware of the concerns that this represents.
 */
public interface EntityClientReconnectHandler {
  /**
   * Called on the thread handling the reconnect to ask if any additional data should be provided to the server-side entity,
   * upon reconnect from this entity.  Note that the value returned here will be provided to the server-side entity via
   * "handleReconnect()"
   * @return Arbitrary data to send the server-side entity when handling the reconnect of this end-point
   */
  public byte[] createExtendedReconnectData();
}
