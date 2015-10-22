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
 *  The Covered Software is Connection API.
 *
 *  The Initial Developer of the Covered Software is
 *  Terracotta, Inc., a Software AG company
 *
 */
package org.terracotta.connection;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import java.util.ServiceLoader;
import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityConstructor;
import org.terracotta.connection.entity.EntityRef;
import org.terracotta.exception.EntityAlreadyExistsException;
import org.terracotta.exception.EntityException;
import org.terracotta.exception.EntityNotFoundException;
import org.terracotta.exception.EntityNotProvidedException;
import org.terracotta.exception.EntityVersionMismatchException;

/**
 * Factory for generating a connection to a stripe.  This is a variation and extension 
 * of ConnectionFactory which includes convenience methods for getOrCreate conventions
 * on the wiring of entities.  note: underling implementation may be optimized on the 
 * server in the future.
 */
public final class EntityConnectionFactory {
  /**
   * Establish a connection based on the given uri. 
   * 
   * @see org.terracotta.connection.ConnectionFactory#connect()
   */
  public static EntityConnection connect(URI uri, Properties properties) throws ConnectionException {
    ServiceLoader<ConnectionService> serviceLoader = ServiceLoader.load(ConnectionService.class,
        EntityConnectionFactory.class.getClassLoader());
    Connection connection = null;
    for (ConnectionService connectionService : serviceLoader) {
      if (connectionService.handlesURI(uri)) {
        connection = connectionService.connect(uri, properties);
        if (connection != null) {
          break;
        }
      }
    }
    if (connection == null) {
      throw new IllegalArgumentException("Unknown URI " + uri);
    } else {
      return new DirectConnectionImpl(connection);
    }
  }
  
  static class DirectConnectionImpl implements EntityConnection {
    private final Connection connection;

    public DirectConnectionImpl(Connection connection) {
      this.connection = connection;
    }

    @Override
    public <T extends Entity, C extends EntityConstructor<T>> T fetchOrCreateEntity(C params) throws EntityException {
      EntityRef<T, C> entityRef = connection.getEntityRef(params.type(), params.version(), params.name());
      while (true) {
        try {
          return entityRef.fetchEntity();
        } catch (EntityNotFoundException notfound) {
//  entity is not found, create it
          try {
            entityRef.create(params);
          } catch (EntityAlreadyExistsException exists) {
//  entity exists, try and fetch it again
          } catch (EntityVersionMismatchException version) {
// this is fatal, rethrow
            throw version;
          } catch (EntityNotProvidedException provide) {
// this is fatal, rethrow
            throw provide;
          }
        } catch (EntityVersionMismatchException version) {
// this is fatal, rethrow
            throw version;
        }
      }
    }

    @Override
    public <T extends Entity, C extends EntityConstructor<T>> boolean destroyEntity(C params) throws EntityException {
      EntityRef<T, C> entityRef = connection.getEntityRef(params.type(), params.version(), params.name());
      if (entityRef != null) {
        entityRef.destroy();
        return true;
      }
      return false;
    }

    @Override
    public <T extends Entity, C> EntityRef<T, C> getEntityRef(Class<T> cls, long version, String name) throws EntityNotProvidedException {
      return connection.getEntityRef(cls, version, name);
    }

    @Override
    public void close() throws IOException {
      connection.close();
    }
    
    
  }
}
