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
import org.terracotta.entity.NoConcurrencyStrategy;
import org.terracotta.entity.ActiveServerEntity;

/**
 * @author twu
 */
public class TestServerEntity implements ActiveServerEntity {
  @Override
  public ConcurrencyStrategy getConcurrencyStrategy() {
    return new NoConcurrencyStrategy();
  }

  @Override
  public void connected(ClientDescriptor clientDescriptor) {
  }

  @Override
  public void disconnected(ClientDescriptor clientDescriptor) {
  }

  @Override
  public byte[] getConfig() {
    return new byte[0];
  }

  @Override
  public byte[] invoke(ClientDescriptor clientDescriptor, byte[] arg) {
    return arg;
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
}
