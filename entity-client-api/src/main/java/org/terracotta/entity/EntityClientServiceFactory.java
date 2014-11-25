package org.terracotta.entity;

import org.terracotta.connection.entity.Entity;

import java.util.ServiceLoader;

/**
 * @author twu
 */
public class EntityClientServiceFactory {
  public static <T extends Entity> EntityClientService<T> creationServiceForType(Class<T> cls) {
    ServiceLoader<EntityClientService> serviceLoader = ServiceLoader.load(EntityClientService.class,
        EntityClientServiceFactory.class.getClassLoader());
    for (EntityClientService entityClientService : serviceLoader) {
      if (entityClientService.handlesEntityType(cls)) {
        return entityClientService;
      }
    }
    throw new IllegalArgumentException("Can't handle entity type " + cls.getName());
  }
}
