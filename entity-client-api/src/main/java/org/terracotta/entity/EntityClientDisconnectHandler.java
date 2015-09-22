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
 * The interface which must be implemented by any object wishing to handle the end-point's unexpected disconnect event.
 * Typically, this is the entity which exists on top of the end-point.
 * 
 * Note that this interface is called on the thread handling realizing the disconnect due to a failed handshake, not the
 * thread running the user code. * Implementors must be aware of the concerns that this represents.
 */
public interface EntityClientDisconnectHandler {
  /**
   * Called on the thread which realized the disconnect.
   */
  public void didDisconnectUnexpectedly();
}
