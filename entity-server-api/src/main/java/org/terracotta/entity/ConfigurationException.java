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
package org.terracotta.entity;


/**
 * An exception type which can be thrown by {@link EntityServerService} when creating or reconfiguring an entity instance
 * to indicate that the configuration could not be satisfied.
 */
public class ConfigurationException extends Exception {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new instance of <code>ConfigurationException</code> without
   * detail message.  
   * 
   * @param description an implementation defined description of the problem
   */
  public ConfigurationException(String description) {
    this(description, null);
  }

  /**
   * Creates a new instance of <code>ConfigurationException</code> along with underlying cause but without
   * detail message.  
   *
   * @param description an implementation defined description of the problem
   * @param cause underlying cause of this exception
   */
  public ConfigurationException(String description, Throwable cause) {
    super(description, cause);
  }
}
