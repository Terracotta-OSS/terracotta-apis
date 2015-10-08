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
   * returns a stream of byte arrays representing the data for the given concurrency key.
   * these byte arrays will be passed to the passive server on sync.
   * 
   * CAUTION: This API is likely to change
   * 
   * @param concurrency key of the data to be sync'd
   * @return see above
   */
  Iterable<byte[]> sync(int concurrency);
  /**
   * ReplicableConcurrencyStrategy is able to return the full set of concurrency
   * keys that would possibly be returned by this strategy.
   * @return 
   */
  @Override
  ReplicableConcurrencyStrategy getConcurrencyStrategy();
}
