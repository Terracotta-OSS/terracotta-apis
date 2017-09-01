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
package org.terracotta.entity;

public interface InvokeContext {
  /**
   * Source instance from which the invocation originates.
   * @return client source
   */
  ClientSourceId getClientSource();

  /**
   * current id for this client that this invoke is part of
   *
   * @return txid, or -1 if unknown.
   */
  long getCurrentTransactionId();

  /**
   * earliest active id for this client; id's older than this
   * can be thrown away.
   *
   * @return txid, or -1 if unknown.
   */
  long getOldestTransactionId();

  /**
   * Does the client information (ClientDescriptor and transaction ids)
   * represent an actual valid client endpoint information or is it merely a marker.
   * @return true if the client information represents a real client.
   */
  boolean isValidClientInformation();

  /**
   * Create a client sourceid from an opaque long.
   * @param opaque
   * @return
   */
  ClientSourceId makeClientSourceId(long opaque);

  /**
   * Return the concurrency key pertaining to this invoke.
   * @return concurrency key of invoke. 
   */
  int getConcurrencyKey();
}
