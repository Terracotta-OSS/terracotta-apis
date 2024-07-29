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
package org.terracotta.connection;

/**
 *  A list of possible properties supported by a connection service.  This list is just advisory.  More
 *  properties may exist for a particular connection implementation.  A connection implementation may not 
 *  implement some or all of these properties.
 */
public interface ConnectionPropertyNames {
  
  /*  a logical name given to a connection at creation */
  String CONNECTION_NAME = "connection.name";
  /*  the time to wait before timing out when attempting to make a connection to the cluster */
  String CONNECTION_TIMEOUT = "connection.timeout";  
  /*  a logical uuid for the connection to the cluster */
  String CONNECTION_UUID = "connection.uuid";  
  /*  requesting no reconnect handling, only server connection */
  String CONNECTION_DISABLE_RECONNECT = "connection.noreconnect";
  /*  request a specific connection type */
  String CONNECTION_TYPE = "connection.type";
  /*  request an async connection */
  String CONNECTION_ASYNC = "connection.async";
}
