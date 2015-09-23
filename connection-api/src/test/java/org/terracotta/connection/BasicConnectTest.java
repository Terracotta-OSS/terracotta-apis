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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;
import org.terracotta.connection.entity.Entity;
import org.terracotta.connection.entity.EntityMaintenanceRef;
import org.terracotta.connection.entity.EntityRef;

public class BasicConnectTest {

  public Connection getConnection() throws ConnectionException, URISyntaxException {
    // tag::connect[]
    Properties properties = new Properties();
    URI uri = new URI("terracotta://localhost:1234");

    Connection connection = ConnectionFactory.connect(uri, properties);
    // end::connect[]
    
    return connection;
  }
  
  @Test
  @Ignore
  public void testCreate() throws ConnectionException, URISyntaxException {
    Connection connection = getConnection();
    
    // tag::create[]
    // Get an maintenance reference on the entity name
    EntityMaintenanceRef<Example, ExampleConfiguration> maintenanceRef =
        connection.acquireMaintenanceModeRef(Example.class, Example.VERSION, "foo");

    // Check if it’s already there
    if (maintenanceRef.doesExist()) {
      // hey, it already exists!
      // either check if it’s actually the one you want, or fail at the fact
      // that there already is something you’re attempting to create, or
      // whatever
    }
    ExampleConfiguration configuration = new ExampleConfiguration();
    // Create it
    maintenanceRef.create(configuration);

    // drop maintenance reference
    maintenanceRef.close();
    // end::create[]
  }
  
  @Test
  @Ignore
  public void testUsing() throws ConnectionException, URISyntaxException {
    Connection connection = getConnection();

    // tag::using[]
    // Get the entity, this locks out someone attempting to enter maintenance
    // mode, it’s blocked by an existing maintenance mode hold.
    EntityRef<Example> entityRef =
        connection.getEntityRef(Example.class, Example.VERSION, "foo");
    Example e = entityRef.fetchEntity();

    // do some stuff
    e.doStuff();
    // end::using[]
  }
  
  @Test
  @Ignore
  public void testDestroy() throws ConnectionException, URISyntaxException {
    Connection connection = getConnection();
    EntityMaintenanceRef<Example, ExampleConfiguration> maintenanceRef =
        connection.acquireMaintenanceModeRef(Example.class, Example.VERSION, "foo");

    // tag::destroy[]
    // same as above...
    maintenanceRef = connection.acquireMaintenanceModeRef(Example.class, Example.VERSION, "foo");

    if (maintenanceRef.doesExist()) {
      maintenanceRef.destroy();
    }
    // end::destroy[]
  }

  interface Example extends Entity {
    public static final long VERSION = 1;
    
    void doSomeMaintenance();

    void doSomeNonMaintenanceStuff();

    void doSomethingElse();

    void doStuff();
  }

  class ExampleConfiguration {
  }
}
