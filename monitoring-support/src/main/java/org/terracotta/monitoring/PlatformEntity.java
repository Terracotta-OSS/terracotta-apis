/*
 * Copyright Terracotta, Inc.
 * Copyright IBM Corp. 2024, 2025
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terracotta.monitoring;

import java.io.Serializable;


/**
 * A type which describes an entity in the stripe.
 */
public class PlatformEntity implements Serializable {
  private static final long serialVersionUID = 8775531936321210074L;

  public String typeName;
  public String name;
  public long consumerID;
  public boolean isActive;

  public PlatformEntity() {
    // For Serializable.
  }

  public PlatformEntity(String typeName, String name, long consumerID, boolean isActive) {
    this.typeName = typeName;
    this.name = name;
    this.consumerID = consumerID;
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("PlatformEntity{");
    sb.append("isActive=").append(isActive);
    sb.append(", typeName='").append(typeName).append('\'');
    sb.append(", consumerID=").append(consumerID);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }

  @Override
  public int hashCode() {
    return this.typeName.hashCode()
        ^ this.name.hashCode()
        ^ (int)consumerID
        ^ (isActive ? 0x1 : 0x0)
      ;
  }

  @Override
  public boolean equals(Object other) {
    boolean doesMatch = (this == other);
    if (!doesMatch && (null != other) && (getClass() == other.getClass()))
    {
      final PlatformEntity that = (PlatformEntity) other;
      doesMatch = this.typeName.equals(that.typeName)
          && this.name.equals(that.name)
          && (this.consumerID == that.consumerID)
          && (this.isActive == that.isActive)
        ;
    }
    return doesMatch;
  }
}
