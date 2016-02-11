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

import org.terracotta.entity.ClientDescriptor;
import org.terracotta.entity.ConcurrencyStrategy;
import org.terracotta.entity.EntityMessage;
import org.terracotta.entity.EntityResponse;
import org.terracotta.entity.NoConcurrencyStrategy;
import org.terracotta.entity.ActiveServerEntity;
import org.terracotta.entity.PassiveSynchronizationChannel;


public class TestServerEntity implements ActiveServerEntity<TestServerEntity.TestMessage, TestServerEntity.TestResponse> {
  public static class TestMessage implements EntityMessage {
    private final byte[] payload;

    public TestMessage(byte[] payload) {
      this.payload = payload;
    }

    public byte[] getPayload() {
      return this.payload;
    }
  }

  public static class TestResponse implements EntityResponse {
    public final byte[] payload;
    
    public TestResponse(byte[] payload) {
      this.payload = payload;
    }
  }

  @Override
  public void connected(ClientDescriptor clientDescriptor) {
  }

  @Override
  public void handleReconnect(ClientDescriptor clientDescriptor, byte[] extendedReconnectData) {
    // Do nothing.
  }

  @Override
  public void disconnected(ClientDescriptor clientDescriptor) {
  }
  
  @Override
  public TestResponse invoke(ClientDescriptor clientDescriptor, TestMessage message) {
    return new TestResponse(message.getPayload());
  }

  @Override
  public void createNew() {
  }

  @Override
  public void loadExisting() {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void synchronizeKeyToPassive(PassiveSynchronizationChannel syncChannel, int concurrencyKey) {
    // TODO:  Add synchronization support.
    throw new AssertionError("Synchronization not supported for this entity");
  }
}
