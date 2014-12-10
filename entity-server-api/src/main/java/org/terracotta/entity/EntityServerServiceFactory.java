package org.terracotta.entity;

import java.util.ServiceLoader;

/**
 * @author twu
 */
public class EntityServerServiceFactory {
  public static <T extends EntityServerService<? extends ServerEntity>> T getService(String typeName) {
    return getService(typeName, EntityServerServiceFactory.class.getClassLoader());
  }

  public static <T extends EntityServerService<? extends ServerEntity>> T getService(String typeName, ClassLoader classLoader) {
    ServiceLoader<EntityServerService> serviceLoader = ServiceLoader.load(EntityServerService.class,
        classLoader);
    for (EntityServerService serverService : serviceLoader) {
      if (serverService.handlesEntityType(typeName)) {
        return (T) serverService;
      }
    }
    throw new IllegalArgumentException("Can't handle entity type " + typeName);
  }
}
