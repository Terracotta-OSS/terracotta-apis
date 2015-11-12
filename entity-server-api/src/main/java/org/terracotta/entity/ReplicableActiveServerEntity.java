/*
 * All content copyright (c) 2014 Terracotta, Inc., except as may otherwise
 * be noted in a separate copyright notice. All rights reserved.
 */
package org.terracotta.entity;

/**
 * This server entity implementation is able to sync across passive servers
 * without implementing this interface, and entity will be created on the passive
 * but no data entity specific data will be sync'd over
 */
// TODO:  This API (passive sync) is expected to significantly change.  At that time, consider removing the default implementation  
public interface ReplicableActiveServerEntity extends ActiveServerEntity {
  /**
   * Passes any information required to describe all entity data/state associated with the given concurrency key to a
   * passive instance being synchronized to be consistent with the receiver.
   * 
   * Note that this method is also run on the concurrencyKey specified, so it blocks other messages executed on that key.
   * 
   * @param syncChannel The output channel to the passive
   * @param concurrencyKey The key of the data to be synchronized
   */
  void synchronizeKeyToPassive(PassiveSynchronizationChannel syncChannel, int concurrencyKey);
}
