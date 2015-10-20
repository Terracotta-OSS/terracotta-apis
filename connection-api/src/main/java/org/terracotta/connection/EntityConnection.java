/*
 * All content copyright (c) 2014 Terracotta, Inc., except as may otherwise
 * be noted in a separate copyright notice. All rights reserved.
 */
package org.terracotta.connection;

import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityConstructor;
import org.terracotta.exception.EntityException;

/**
 * EntityConnection is a variation on Connection with added convenience methods for 
 * create and destroy.  Use {@link} org.terracotta.connection.Connection methods for 
 * direct control of creation semantics on the server.  
 */
public interface EntityConnection extends Connection {
  /**
   * createEntity has get or create semantics in implementation.  This really means create a
   * unique instance of this entity for this client.  Multiple calls to this method will return multiple 
   * client instances of this entity.  On the server, if the name/type 
   * combination designated by the {@link} org.terracotta.connection.entity.EntityConstructor
   * is already in existence, then the existing entity wiring will be returned.  If the
   * entity needs construction on the server, the {@link} org.terracotta.connection.entity.EntityConstructor
   * will be passed to the server for construction.  
   * 
   * @param <T> type of the entity being wired
   * @param <C> type of the {@link} org.terracotta.connection.entity.EntityConstructor
   * @param params Information used to wire the entity {@link} org.terracotta.connection.entity.EntityConstructor
   * @return the requested entity. It may have been created in the processing of this call
   * @throws EntityException exception the occurred in the fetching or creation of the entity
   */
  <T extends Entity, C extends EntityConstructor<T>> T fetchOrCreateEntity(C params) throws EntityException;
  /**
   * destroyEntity is called directly on connection as it requires exclusive access to the entity.  
   * only if the desired entity is not referenced by any clients is the entity able to unload and 
   * be destroyed.  Only call this method to remove all traces of this entity from clients and servers.
   * 
   * @param <T> Type of entity being destroyed
   * @param <C> type of the {@link} org.terracotta.connection.entity.EntityConstructor
   * @param params Information used to destroy the entity {@link} org.terracotta.connection.entity.EntityConstructor
   * @return true of entity was destroyed
   * @throws EntityException 
   */
  <T extends Entity, C extends EntityConstructor<T>> boolean destroyEntity(C params) throws EntityException;
}
