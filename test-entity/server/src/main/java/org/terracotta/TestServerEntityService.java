package org.terracotta;

import org.terracotta.entity.PassiveServerEntity;
import org.terracotta.entity.ServerEntityService;
import org.terracotta.entity.ServiceRegistry;

/**
 * @author twu
 */
public class TestServerEntityService implements ServerEntityService<TestServerEntity, PassiveServerEntity> {
  @Override
  public boolean handlesEntityType(String typeName) {
    return "org.terracotta.TestEntity".equals(typeName);
  }

  @Override
  public TestServerEntity createActiveEntity(ServiceRegistry registry, byte[] configuration) {
    return new TestServerEntity();
  }

  @Override
  public PassiveServerEntity createPassiveEntity(ServiceRegistry registry, byte[] configuration) {
    throw new UnsupportedOperationException();
  }
}
