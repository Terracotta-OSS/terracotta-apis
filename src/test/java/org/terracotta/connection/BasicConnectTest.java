/*
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved.
 */
package org.terracotta.connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;
import org.terracotta.connection.entity.ConfigurationMismatchException;
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
        connection.acquireMaintenanceModeRef(Example.class, "foo");

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
    EntityRef<Example, ExampleConfiguration> entityRef =
        connection.getEntityRef(Example.class, "foo");
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
        connection.acquireMaintenanceModeRef(Example.class, "foo");

    // tag::destroy[]
    // same as above...
    maintenanceRef = connection.acquireMaintenanceModeRef(Example.class, "foo");

    if (maintenanceRef.doesExist()) {
      maintenanceRef.destroy();
    }
    // end::destroy[]
  }
  
  @Test
  @Ignore
  public void testConnectionLoss() throws ConnectionException, URISyntaxException {
    Connection connection;
    Properties properties = new Properties();
    URI uri;

    // tag::handleConnectionLoss[]
    uri = new URI("terracotta://localhost:1234");
    connection = ConnectionFactory.connect(uri, new DisconnectHandler() {
      @Override
      public void connectionLost(URI uri) {
        // Umm... don't do this.
        System.exit(1);
      }
    }, properties);
    // end::handleConnectionLoss[]
  }

  interface Example extends Entity {
    void doSomeMaintenance();

    void doSomeNonMaintenanceStuff();

    void doSomethingElse();

    void doStuff();
  }

  class ExampleConfiguration {
  }
}
