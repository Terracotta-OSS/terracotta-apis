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
 *  The Covered Software is Connection API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.exception;

/**
 *
 */
public class PermanentEntityException extends EntityNotFoundException {

  /**
   * Creates a new instance of <code>PermanentEntityException</code> without
   * detail message.  This exception is thrown when a client attempts to delete a
   * permanent entity created on the server.
   * 
   * @param className class of the entity that was being deleted
   * @param entityName name of the entity being deleted
   */
  public PermanentEntityException(String className, String entityName) {
    super(className, entityName);
  }
}
