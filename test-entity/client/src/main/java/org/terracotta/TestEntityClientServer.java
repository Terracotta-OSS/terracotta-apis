package org.terracotta;

import org.terracotta.entity.EntityClientEndpoint;
import org.terracotta.entity.EntityClientService;

import java.io.Serializable;

/**
 * @author twu
 */
public class TestEntityClientServer implements EntityClientService<TestEntity> {
  @Override
  public boolean handlesEntityType(Class<TestEntity> cls) {
    return cls == TestEntity.class;
  }

  @Override
  public TestEntity create(EntityClientEndpoint endpoint, Serializable configuration) {
    return new TestEntityClient();
  }
}
