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
 * The methods specifically supported by passive entities.  Note that a passive doesn't know anything about
 *  connected clients, nor can it return data to them.
 */
public interface PassiveServerEntity extends CommonServerEntity {
  /**
   * Invoke a call on the given entity.  Note that passive entities can't return data to the client.
   *
   * @param arg entity specific invocation info
   */
  void invoke(byte[] arg);
}
