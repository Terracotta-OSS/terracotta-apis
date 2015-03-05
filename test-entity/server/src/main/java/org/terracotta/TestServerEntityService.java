package org.terracotta;

import org.terracotta.entity.ServerEntityService;
import org.terracotta.entity.ServiceRegistry;

/**
 * @author twu
 */
public class TestServerEntityService implements ServerEntityService<TestServerEntity> {
  @Override
  public boolean handlesEntityType(String typeName) {
    return "org.terracotta.TestEntity".equals(typeName);
  }

  @Override
  public TestServerEntity createEntity(ServiceRegistry registry, byte[] configuration) {
    return new TestServerEntity();
  }

  @Override
  public TestServerEntity getEntity(ServiceRegistry registry) {
    return new TestServerEntity();
  }
}
