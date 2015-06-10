package org.terracotta;

import com.tc.object.EntityID;
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
  public TestServerEntity createActiveEntity(EntityID id, ServiceRegistry registry, byte[] configuration) {
    return new TestServerEntity();
  }

  @Override
  public PassiveServerEntity createPassiveEntity(EntityID id, ServiceRegistry registry, byte[] configuration) {
    throw new UnsupportedOperationException();
  }
}
