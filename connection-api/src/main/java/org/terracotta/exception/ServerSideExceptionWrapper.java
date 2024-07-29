/*
 * Copyright Terracotta, Inc.
 * Copyright Super iPaaS Integration LLC, an IBM Company 2024
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
