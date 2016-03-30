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

import org.terracotta.connection.entity.Entity;

import java.util.ServiceLoader;


/**
 * Loads and creates the EntityClientService instances for specific entity types.
 */
public class EntityClientServiceFactory {
  /**
   * Finds the service for the given entity type, in the class loader of EntityClientServiceFactory.
   * 
   * @param cls The entity class type
   * @return The EntityClientService to create client-side entities of this type
   */
  public static <T extends Entity, C> EntityClientService<T, C, ? extends EntityMessage, ? extends EntityResponse> creationServiceForType(Class<T> cls) {
    return creationServiceForType(cls, EntityClientServiceFactory.class.getClassLoader());
  }

  /**
   * Finds the service for the given entity type, in the given class loader.
   * 
   * @param cls The entity class type
   * @param classLoader The class loader where the type should be searched
   * @return The EntityClientService to create client-side entities of this type
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static <T extends Entity, C> EntityClientService<T, C, ? extends EntityMessage, ? extends EntityResponse> creationServiceForType(Class<T> cls, ClassLoader classLoader) {
    ServiceLoader<EntityClientService> serviceLoader = ServiceLoader.load(EntityClientService.class, classLoader);
    for (EntityClientService<T, C, ? extends EntityMessage, ? extends EntityResponse> entityClientService : serviceLoader) {
      if (entityClientService.handlesEntityType(cls)) {
        return entityClientService;
      }
    }
    throw new IllegalArgumentException("Can't handle entity type " + cls.getName());
  }
}
