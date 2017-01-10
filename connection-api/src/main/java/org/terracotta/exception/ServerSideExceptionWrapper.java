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


/**
 * This exception type exists purely in place of exceptions which may be thrown by server-side user-provided code but
 *  may not be deserializable on the client (it not being packaged on the client class path, for example).
 * This type is used in place of any cause exceptions beneath our top-level exception types which can be serialized
 *  for transmission to the client.
 */
public class ServerSideExceptionWrapper extends Exception {
  private static final long serialVersionUID = 1L;

  public static ServerSideExceptionWrapper buildFromThrowable(Throwable t) {
    Throwable cause = t.getCause();
    ServerSideExceptionWrapper wrappedCause = null;
    if (null != cause) {
      wrappedCause = ServerSideExceptionWrapper.buildFromThrowable(cause);
    }
    // We want to use the name of the original exception type in the description of this new type.
    String originalExceptionTypeName = t.getClass().getName();
    String description = originalExceptionTypeName + ": " + t.getLocalizedMessage();
    ServerSideExceptionWrapper wrapper = new ServerSideExceptionWrapper(description, wrappedCause);
    wrapper.setStackTrace(t.getStackTrace());
    return wrapper;
  }


  private ServerSideExceptionWrapper(String description, Throwable cause) {
    super(description, cause);
  }
}
