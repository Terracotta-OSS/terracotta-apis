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

/**
 * Abstraction for dumping a component's state using key-value mappings in hierarchical manner
 *
 * <p><b>Typical usage</b>
 * <pre>
 *   StateDumpCollector topLevelStateDumpCollector;
 *   StateDumpCollector ehcacheEntityDumpCollector = topLevelStateDumpCollector.subStateDumpCollector("ehcache");
 *   StateDumpCollector flatFileStorageDumpCollector = topLevelStateDumpCollector.subStateDumpCollector("flatFileStorage");
 *   StateDumpCollector locationFlatFileStorageDumpCollector = flatFileStorageDumpCollector.subStateDumpCollector("location");
 *   locationFlatFileStorageDumpCollector.addState("storageDir", "/var/log/flatfiledir");
 *   StateDumpCollector storageUsageFlatFileStorageDumpCollector = flatFileStorageDumper.subStateDumpCollector("storage");
 *   storageUsageFlatFileStorageDumpCollector.addState("used-space", "100MB");
 *   storageUsageFlatFileStorageDumpCollector.addState("free-space", "900MB");
 *   storageUsageFlatFileStorageDumpCollector.addState("allocated-space", "1000MB");
 * </pre></p>
 *
 *
 * @author vmad
 */
public interface StateDumpCollector {

   public static final String NAMESPACE_DELIMITER = ".";

  /**
   * Returns a namespace'd {@link StateDumpCollector}
   *
   * @param name Sub {@link StateDumpCollector} name, should not contain {@link #NAMESPACE_DELIMITER}
   * @return A {@link StateDumpCollector}
   */
  StateDumpCollector subStateDumpCollector(String name);

  /**
   * Adds given key-value mapping to this {@link StateDumpCollector}
   *
   * @param key      key name with the value to be associated
   * @param value    the value
   */
  void addState(String key, String value);
}
