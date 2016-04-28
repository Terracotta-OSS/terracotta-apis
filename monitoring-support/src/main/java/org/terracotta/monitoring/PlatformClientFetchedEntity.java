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
 *  The Covered Software is Terracotta Core.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.monitoring;

import com.tc.classloader.CommonComponent;
import org.terracotta.entity.ClientDescriptor;


/**
 * A type which describes the connection between a client and an entity, created by a fetch.
 */
@CommonComponent
public class PlatformClientFetchedEntity {
  public final String clientIdentifier;
  public final String entityIdentifier;
  public final ClientDescriptor clientDescriptor;

  public PlatformClientFetchedEntity(String clientIdentifier, String entityIdentifier, ClientDescriptor clientDescriptor) {
    this.clientIdentifier = clientIdentifier;
    this.entityIdentifier = entityIdentifier;
    this.clientDescriptor = clientDescriptor;
  }
}
