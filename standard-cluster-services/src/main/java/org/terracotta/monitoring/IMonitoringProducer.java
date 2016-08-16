package org.terracotta.monitoring;

import com.tc.classloader.CommonComponent;


/**
 * The interface exposed by a monitoring service, allowing entities to push statistics and structured data which can be
 * consumed by an external component (attached to the other side of the service, using some more specialized interface).
 */
@CommonComponent
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
  public boolean addNode(String[] parents, String name, Object value);

  /**
   * Removes a node from the internal data registry tree.
   * 
   * @param parents The parent node names, starting from the root.
   * @param name The name of the node to remove.
   * @return True if the node was removed.  False if it or a parent couldn't be found.
   */
  public boolean removeNode(String[] parents, String name);

  /**
   * Makes a best-efforts attempt to push named data to the interface.  This method differs from the add/remove node methods in
   *  that the implementation is allowed to drop, ignore, or overwrite the data at any time.
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
  public void pushBestEffortsData(String name, Object data); 
}
