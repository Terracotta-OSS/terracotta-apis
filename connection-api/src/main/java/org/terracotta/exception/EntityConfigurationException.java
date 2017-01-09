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
package org.terracotta.exception;

import java.io.ObjectStreamException;


/**
 * This specific EntityException type is thrown in cases where an exception was thrown from user code associated
 * with the entity during a lifecycle operation.
 */
public class EntityConfigurationException extends EntityException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates the exception instance describing the given type-name pair and the specific underlying exception from the user
   * code.
   * 
   * @param className The name of the entity type
   * @param entityName The name of the entity instance
   * @param cause The underlying throwable thrown by the user code
   */
  public EntityConfigurationException(String className, String entityName, Throwable cause) {
    super(className, entityName, "lifecycle exception: " + cause.getLocalizedMessage(), cause);
  }

  public Object writeReplace() throws ObjectStreamException {
    // We jump in here to convert the underlying cause (if there is one) to a type which we know won't depend on
    //  server-side classpath.  We then return a new instance of ourself which wraps that exception.
    Object toSerialize = this;
    Throwable cause = this.getCause();
    if (null != cause) {
      ServerSideExceptionWrapper wrappedCause = ServerSideExceptionWrapper.buildFromThrowable(cause);
      toSerialize = new EntityConfigurationException(this.getClassName(), this.getEntityName(), wrappedCause);
    }
    return toSerialize;
  }
}
