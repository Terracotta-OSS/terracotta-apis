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
 *  The Covered Software is Entity API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.monitoring;

import java.io.Serializable;


/**
 * The interface exposed by a monitoring service, allowing entities to push statistics and structured data which can be
 *  consumed by an external component.
 * 
 * The implementation of this interface is provided by the server implementation, itself.  A monitoring component must
 *  provide an IStripeMonitoring implementation to receive the data passed into this interface.
 * 
 * Note that the values used in these methods must be Serializable since the implementation may need to send them over a
 *  wire.
 */
public interface IMonitoringProducer {
  /**
   * Adds a node to the internal data registry tree or replaces an existing one.
   * By default, new nodes have no children.
   * 
   * @param parents The parent node names, starting from the root.
   * @param name The name of the node to create or replace.
   * @param value The value to set for the new node.
   * @return True if the node was created/replaced.  False if a parent couldn't be found.
   */
  public boolean addNode(String[] parents, String name, Serializable value);

  /**
   * Removes a node from the internal data registry tree.
   * 
   * @param parents The parent node names, starting from the root.
   * @param name The name of the node to remove.
   * @return True if the node was removed.  False if it or a parent couldn't be found.
   */
  public boolean removeNode(String[] parents, String name);

  /**
   * Makes a best-efforts attempt to push named data to the interface.  This method differs from the add/remove node methods
   *  in that the implementation is allowed to drop, ignore, or overwrite the data at any time.
   * This interface is more appropriate for information such as statistics or other sampling information which may be dropped
   *  if the system is busy or otherwise saturated.
   * The implementation is free to impose whatever limiting heuristics it desires, meaning that the limiting may be based on
   *  storage size, element count, staleness before send, or any other approach.  Additionally, the limit may be applied
   *  globally or on a per-name basis.
   * 
   * @param name A name given to identify the data.  An implementation may use this as its limiting heuristic, to ensure that
   *  infrequent data is not disproportionately over-written by very frequent data.
   * @param data The object to push.
   */
  public void pushBestEffortsData(String name, Serializable data); 
}
