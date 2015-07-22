/*
 *
 *  The contents of this file are subject to the Terracotta Public License Version
 *  2.0 (the "License"); You may not use this file except in compliance with the
 *  License. You may obtain a copy of the License at
 *
 *  http://terracotta.org/legal/terracotta-public-license.
 *
 *  Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 *  the specific language governing rights and limitations under the License.
 *
 *  The Covered Software is Entity API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */

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
