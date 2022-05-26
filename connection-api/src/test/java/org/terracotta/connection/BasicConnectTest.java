/*
 * Copyright (c) 2011-2018 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
 * Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.
 */

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
import org.terracotta.connection.entity.EntityRef;
import org.terracotta.exception.EntityException;


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
  public void testCreate() throws ConnectionException, URISyntaxException, EntityException {
    Connection connection = getConnection();

    // tag::create[]
    // Get a reference on the entity name
    EntityRef<Example, ExampleConfiguration, Object> ref = connection.getEntityRef(Example.class, Example.VERSION, "foo");
    ExampleConfiguration configuration = new ExampleConfiguration();
    // Create it
    ref.create(configuration);
    // end::create[]
  }

  @Test
  @Ignore
  public void testUsing() throws ConnectionException, URISyntaxException, EntityException {
    Connection connection = getConnection();

    // tag::using[]
    // Get the entity, this locks out someone attempting to enter maintenance
    // mode, it’s blocked by an existing maintenance mode hold.
    EntityRef<Example, ExampleConfiguration, Object> entityRef = connection.getEntityRef(Example.class, Example.VERSION, "foo");
    Example e = entityRef.fetchEntity(new Object());
    // do some stuff
    e.doStuff();
    // Close.
    e.close();
    // end::using[]
  }

  @Test
  @Ignore
  public void testDestroy() throws ConnectionException, URISyntaxException, EntityException {
    Connection connection = getConnection();
    EntityRef<Example, ExampleConfiguration, Object> ref = connection.getEntityRef(Example.class, Example.VERSION, "foo");

    // tag::destroy[]
    // See if we can destroy the entity.
    ref.destroy();
    // end::destroy[]
  }

  interface Example extends Entity {
    public static final long VERSION = 1;

    void doSomeMaintenance();

    void doSomeNonMaintenanceStuff();

    void doSomethingElse();

    void doStuff();
  }

  static class ExampleConfiguration {
  }
}
