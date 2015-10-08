/*
 * All content copyright (c) 2014 Terracotta, Inc., except as may otherwise
 * be noted in a separate copyright notice. All rights reserved.
 */
package org.terracotta.entity;

/**
 * This is iterable for Passive Sync.  The iterator is to return each possible concurrency key 
 * once and only once for the ConcurrencyStrategy implementation.  Each iteration is feed to the @see ActiveServerEntity#sync(int)
 * which in turn, provides an iteration of that segment of data controlled by that concurrency key
 */
// TODO:  This API (passive sync) is expected to significantly change.  At that time, consider removing the default implementation  
public interface ReplicableConcurrencyStrategy extends ConcurrencyStrategy, Iterable<Integer> {
  
}
