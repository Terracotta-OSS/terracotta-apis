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
package org.terracotta.connection.entity;


/**
 * The constructor object is passed to EntityConnction.  This object is responsible
 * for designating the version, name and type of the entity fetched or created.  The
 * entity is unique based on name and type only.  Version is only a check that the 
 * correct version of entity is loaded.  EntityConstructor as a whole is passed to the
 * server on creation so additional, entity specific construction data may be passed 
 * to the server using this object.
 * @param <T> Type of the entity to be connected
 */
public interface EntityConstructor<T extends Entity> {
  /**
   * Note that the version must be an exact match to succeed in resolving the entity instance, on the server.
   * 
   * @return the version of the entity to be connected
   */
  long version();

  /**
   * The name specifies one part of the unique identification of an entity (the type being the other part).
   * 
   * @return name of the entity to be mapped
   */
  String name();

  /**
   * The type specifies one part of the unique identification of an entity (the name being the other part).
   * 
   * @return type of the entity to be connect
   */
  Class<T> type();
}
