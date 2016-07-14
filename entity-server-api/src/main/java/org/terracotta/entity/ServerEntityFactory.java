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

import java.util.ServiceLoader;


/**
 * Loads and creates the ActiveServerEntity and PassiveServerEntity instances, on the server, for specific entity types.
 */
public class ServerEntityFactory {
  /**
   * Finds the service for the given entity type, in the class loader of ServerEntityFactory.
   * 
   */
  public static <T extends EntityServerService<? extends EntityMessage, ? extends EntityResponse>> T getService(String typeName) {
    return getService(typeName, ServerEntityFactory.class.getClassLoader());
  }

  /**
   * Finds the service for the given entity type, in the given class loader.
   * 
   * @param typeName The entity type name
   * @param classLoader The class loader where the type should be searched
   * @return The ServerEntityFactory to create server-side entities of this type
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static <T extends EntityServerService<? extends EntityMessage, ? extends EntityResponse>> T getService(String typeName, ClassLoader classLoader) {
    ServiceLoader<EntityServerService> serviceLoader = ServiceLoader.load(EntityServerService.class, classLoader);
    for (EntityServerService serverService : serviceLoader) {
      if (serverService.handlesEntityType(typeName)) {
        return (T) serverService;
      }
    }
    throw new IllegalArgumentException("Can't handle entity type " + typeName);
  }
}
