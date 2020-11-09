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
 *  The Covered Software is Terracotta API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
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
