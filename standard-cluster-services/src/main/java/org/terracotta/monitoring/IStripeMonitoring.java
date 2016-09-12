package org.terracotta.monitoring;

import java.io.Serializable;

import com.tc.classloader.CommonComponent;


/**
 * The interface which must be implemented by a monitoring component in order to receive the data entities passed into
 *  IMonitoringProducer, on a server within the stripe.
 * 
 * Note that only the implementation on the current active server will receive this data but it will receive the data from
 *  the entire stripe.
 * 
 * Note that the values used in these methods are Serializable since they may have come over the wire.
 */
@CommonComponent
public interface IStripeMonitoring {
  /**
   * Adds a node to the internal data registry tree or replaces an existing one.
   * By default, new nodes have no children.
   * 
   * @param sender The description of the server where the call originated.
   * @param parents The parent node names, starting from the root.
   * @param name The name of the node to create or replace.
   * @param value The value to set for the new node.
   * @return True if the node was created/replaced.  False if a parent couldn't be found.
   */
  public boolean addNode(PlatformServer sender, String[] parents, String name, Serializable value);

  /**
   * Removes a node from the internal data registry tree.
   * 
   * @param sender The description of the server where the call originated.
   * @param parents The parent node names, starting from the root.
   * @param name The name of the node to remove.
   * @return True if the node was removed.  False if it or a parent couldn't be found.
   */
  public boolean removeNode(PlatformServer sender, String[] parents, String name);

  /**
   * Makes a best-efforts attempt to push named data to the interface.  This method differs from the add/remove node methods
   *  in that the implementation is allowed to drop, ignore, or overwrite the data at any time.
   * This interface is more appropriate for information such as statistics or other sampling information which may be dropped
   *  if the system is busy or otherwise saturated.
   * The implementation is free to impose whatever limiting heuristics it desires, meaning that the limiting may be based on
   *  storage size, element count, staleness before send, or any other approach.  Additionally, the limit may be applied
   *  globally or on a per-name basis.
   * 
   * @param sender The description of the server where the call originated.
   * @param name A name given to identify the data.  An implementation may use this as its limiting heuristic, to ensure that
   *  infrequent data is not disproportionately over-written by very frequent data.
   * @param data The object to push.
   */
  public void pushBestEffortsData(PlatformServer sender, String name, Serializable data); 
}
