package org.terracotta;

import org.terracotta.entity.EntityClientEndpoint;
import org.terracotta.entity.EntityClientService;

/**
 * @author twu
 */
public class TestEntityClientServer implements EntityClientService<TestEntity, TestEntityConfig> {
  @Override
  public boolean handlesEntityType(Class<TestEntity> cls) {
    return cls == TestEntity.class;
  }

  @Override
  public byte[] serializeConfiguration(TestEntityConfig configuration) {
    return configuration.toBytes();
  }

  @Override
  public TestEntityConfig deserializeConfiguration(byte[] configuration) {
    return TestEntityConfig.fromBytes(configuration);
  }

  @Override
  public TestEntity create(EntityClientEndpoint endpoint) {
    return new TestEntityClient();
  }
}
