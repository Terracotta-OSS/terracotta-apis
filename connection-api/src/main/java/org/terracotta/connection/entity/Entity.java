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
 *  The Covered Software is Connection API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.connection.entity;

import java.io.Closeable;


/**
 * <p>An instance of this type represents a concrete connection to a server-side entity.  The entity always exists so
 * long as an instance of this type is open to it.  This also means that the open instance will block any attempts to
 * delete the server-side instance.</p>
 * 
 * <p>Therefore, {@link Entity#close()} must be called in order to release this hold on the entity.</p>
 */
public interface Entity extends Closeable {
  /**
   * <p>Release this handle on the entity. The instance will be unusable after this call.</p>
   * 
   * <p>Note:  The implementation is required to close its underlying {@link EntityRef} within this call so the server
   * can be notified that the client-side reference has been released.</p>
   */
  @Override
  void close();
}
