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
 *  ExecutionStrategy helps the stripe determine if a particular message should 
 *  be executed on the active server, passive server or both
 */
public interface ExecutionStrategy<M extends EntityMessage> {
  enum Location {
    ACTIVE {
      @Override
      public boolean runOnPassive() {
        return false;
      }
    }, PASSIVE {
      @Override
      public boolean runOnActive() {
        return false;
      }
    }, BOTH {

    }, NONE {
      @Override
      public boolean runOnActive() {
        return false;
      }

      @Override
      public boolean runOnPassive() {
        return false;
      }
    }, IGNORE;  // IGNORE is for internal use only
    
    public boolean runOnPassive() {
      return true;
    }
    
    public boolean runOnActive() {
      return true;
    }
  }
  
  Location getExecutionLocation(M message);
}
