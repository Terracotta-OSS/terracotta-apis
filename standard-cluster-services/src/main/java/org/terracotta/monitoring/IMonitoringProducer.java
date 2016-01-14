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
}
