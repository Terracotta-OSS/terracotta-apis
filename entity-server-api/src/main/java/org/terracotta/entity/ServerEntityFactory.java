package org.terracotta.entity;

import java.util.ServiceLoader;

/**
 * @author twu
 */
public class ServerEntityFactory {
  public static <T extends ServerEntityService<? ,? extends ActiveServerEntity, ? extends PassiveServerEntity>> T getService(String typeName) {
    return getService(typeName, ServerEntityFactory.class.getClassLoader());
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static <T extends ServerEntityService<? ,? extends ActiveServerEntity, ? extends PassiveServerEntity>> T getService(String typeName, ClassLoader classLoader) {
    ServiceLoader<ServerEntityService> serviceLoader = ServiceLoader.load(ServerEntityService.class, classLoader);
    for (ServerEntityService serverService : serviceLoader) {
      if (serverService.handlesEntityType(typeName)) {
        return (T) serverService;
      }
    }
    throw new IllegalArgumentException("Can't handle entity type " + typeName);
  }
}
