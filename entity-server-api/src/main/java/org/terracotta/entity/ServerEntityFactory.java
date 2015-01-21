package org.terracotta.entity;

import java.io.Serializable;
import java.util.ServiceLoader;

/**
 * @author twu
 */
public class ServerEntityFactory {
  public static <T extends ServerEntityService<? extends ServerEntity>> T getService(String typeName) {
    return getService(typeName, ServerEntityFactory.class.getClassLoader());
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static <T extends ServerEntityService<? extends ServerEntity>> T getService(String typeName, ClassLoader classLoader) {
    ServiceLoader<ServerEntityService> serviceLoader = ServiceLoader.load(ServerEntityService.class, classLoader);
    for (ServerEntityService serverService : serviceLoader) {
      if (serverService.handlesEntityType(typeName)) {
        return (T) serverService;
      }
    }
    throw new IllegalArgumentException("Can't handle entity type " + typeName);
  }

  @SuppressWarnings("unchecked")
  public static <T extends ServerEntity> T getEntity(String typeName, ClassLoader classLoader,
                                                     ServiceRegistry serviceRegistry) {
    return (T) getService(typeName, classLoader).getEntity(serviceRegistry);
  }

  @SuppressWarnings("unchecked")
  public static <T extends ServerEntity> T createEntity(String typeName, ClassLoader classLoader,
                                                        ServiceRegistry serviceRegistry, Serializable configuration) {
    return (T) getService(typeName, classLoader).createEntity(serviceRegistry, configuration);
  }
}
