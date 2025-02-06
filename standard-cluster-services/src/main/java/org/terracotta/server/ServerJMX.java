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
package org.terracotta.server;

import javax.management.MBeanServer;

/**
 *
 */
public interface ServerJMX {
    /**
     * get an attribute from the mbean server
     * @param target registered object
     * @param attr desired attribute
     * @return attribute value
     */
    String get(String target, String attr);
    /**
     * set an attribute from the mbean server
     * @param target registered object
     * @param attr desired attribute
     * @param val desired value
     * @return result of the setter operation (typically "SUCCESS" or a text version of an error
     */
    String set(String target, String attr, String val);
    /**
     * call a command on an mbean
     * @param target registered object
     * @param cmd desired attribute
     * @param arg desired value
     * @return result of the setter operation (typically "SUCCESS" or a text version of an error
     */
    String call(String target, String cmd, String arg);
    /**
     * Register in the public domain
     * @param name name
     * @param target object to service the mbean
     */
    void registerMBean(String name, Object target);
    /**
     * the mbean server for this server
     * @return server's mbean
     */
    MBeanServer getMBeanServer();
}
