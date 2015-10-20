/*
 * All content copyright (c) 2014 Terracotta, Inc., except as may otherwise
 * be noted in a separate copyright notice. All rights reserved.
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
   * 
   * @return the version of the entity to be connected
   */
  long version();
  /**
   * 
   * @return name of the entity to be mapped
   */
  String name();
  /**
   * 
   * @return type of the entity to be connect
   */
  Class<T> type();
}
