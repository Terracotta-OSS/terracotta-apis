package org.terracotta;

import org.terracotta.entity.PassiveServerEntity;
import org.terracotta.entity.ServerEntityService;
import org.terracotta.entity.ServiceRegistry;


public class TestServerEntityService implements ServerEntityService<TestEntityID ,TestServerEntity, PassiveServerEntity> {
  @Override
  public long getVersion() {
    return TestEntity.VERSION;
  }

  @Override
  public boolean handlesEntityType(String typeName) {
    return "org.terracotta.TestEntity".equals(typeName);
  }

  @Override
  public TestServerEntity createActiveEntity(TestEntityID id, ServiceRegistry registry, byte[] configuration) {
    return new TestServerEntity();
  }

  @Override
  public PassiveServerEntity createPassiveEntity(TestEntityID id, ServiceRegistry registry, byte[] configuration) {
    throw new UnsupportedOperationException();
  }
}
