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
}
