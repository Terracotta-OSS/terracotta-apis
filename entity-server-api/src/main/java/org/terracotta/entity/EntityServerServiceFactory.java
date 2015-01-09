package org.terracotta.entity;

import java.util.ServiceLoader;

/**
 * @author twu
 */
public class EntityServerServiceFactory {
  public static <T extends ServerEntityService<? extends ServerEntity>> T getService(String typeName) {
    return getService(typeName, EntityServerServiceFactory.class.getClassLoader());
  }

  public static <T extends ServerEntityService<? extends ServerEntity>> T getService(String typeName, ClassLoader classLoader) {
    ServiceLoader<ServerEntityService> serviceLoader = ServiceLoader.load(ServerEntityService.class,
        classLoader);
    for (ServerEntityService serverService : serviceLoader) {
      if (serverService.handlesEntityType(typeName)) {
        return (T) serverService;
      }
    }
    throw new IllegalArgumentException("Can't handle entity type " + typeName);
  }
}
