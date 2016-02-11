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

import org.terracotta.entity.ConcurrencyStrategy;
import org.terracotta.entity.EntityMessage;
import org.terracotta.entity.EntityResponse;
import org.terracotta.entity.MessageCodec;
import org.terracotta.entity.MessageCodecException;
import org.terracotta.entity.NoConcurrencyStrategy;
import org.terracotta.entity.PassiveServerEntity;
import org.terracotta.entity.ServerEntityService;
import org.terracotta.entity.ServiceRegistry;


public class TestServerEntityService implements ServerEntityService<TestServerEntity.TestMessage, TestServerEntity.TestResponse> {
  @Override
  public long getVersion() {
    return TestEntity.VERSION;
  }

  @Override
  public boolean handlesEntityType(String typeName) {
    return "org.terracotta.TestEntity".equals(typeName);
  }

  @Override
  public TestServerEntity createActiveEntity(ServiceRegistry registry, byte[] configuration) {
    return new TestServerEntity();
  }

  @Override
  public PassiveServerEntity<TestServerEntity.TestMessage, TestServerEntity.TestResponse> createPassiveEntity(ServiceRegistry registry, byte[] configuration) {
    throw new UnsupportedOperationException();
  }

  @Override
  public MessageCodec<TestServerEntity.TestMessage, TestServerEntity.TestResponse> getMessageCodec() {
    return new MessageCodec<TestServerEntity.TestMessage, TestServerEntity.TestResponse>() {
      @Override
      public TestServerEntity.TestMessage deserialize(byte[] payload) {
        return new TestServerEntity.TestMessage(payload);
      }

      @Override
      public TestServerEntity.TestMessage deserializeForSync(int concurrencyKey, byte[] payload) {
        // TODO:  Add synchronization support.
        throw new AssertionError("Synchronization not supported for this entity");
      }

      @Override
      public byte[] serialize(TestServerEntity.TestResponse response) {
        return response.payload;
      }

      @Override
      public byte[] serializeForSync(int concurrencyKey, TestServerEntity.TestResponse payload) throws MessageCodecException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
      
      
    };
  } 
}
