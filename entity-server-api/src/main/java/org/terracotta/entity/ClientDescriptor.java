/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 */
package org.terracotta.entity;

/**
 * An opaque token representing a specific entity instance on a specific client node.
 * This is used by server-side code to specifically communicate with or track connection status of a specific client-side
 * object instance.
 * Note that implementations are expected to provide a proper equals()/hashCode().
 */
public interface ClientDescriptor {
}
