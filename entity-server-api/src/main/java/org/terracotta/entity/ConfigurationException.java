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
package org.terracotta.entity;

/**
 *
 */
public class ConfigurationException extends Exception {
  /**
   * Creates a new instance of <code>ConfigurationException</code> without
   * detail message.  This exception is thrown when a client attempts to delete a
   * permanent entity created on the server.
   * 
   * @param description an implementation defined description of the problem
   */
  public ConfigurationException(String description) {
    this(description, null);
  }

  /**
   * Creates a new instance of <code>EntityConfigurationException</code> along with underlying cause but without
   * detail message.  This exception is thrown when a client attempts to delete a
   * permanent entity created on the server.
   *
   * @param description an implementation defined description of the problem
   * @param cause underlying cause of this exception
   */
  public ConfigurationException(String description, Throwable cause) {
    super(description, cause);
  }
}
