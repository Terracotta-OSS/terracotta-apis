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
 * Represents a client, which may have multiple ClientDescriptors being used through it.
 * Implementations are required to implement equals()/hashcode() properly, and equality
 * of the opaque long must be equivalent to the equality of the instance objects. 
 */
public interface ClientSourceId {
  /**
   * Opaque long representation of this source.
   * @return long
   */
  long toLong();

  /**
   * Whether a specific ClientDescriptor is tied to this ClientSource
   * @param cd
   * @return true if this descriptor belongs to this client source
   */
  boolean matches(ClientDescriptor cd);
}
