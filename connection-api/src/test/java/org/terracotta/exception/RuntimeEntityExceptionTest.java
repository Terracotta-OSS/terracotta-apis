/*
 * Copyright Terracotta, Inc.
 * Copyright IBM Corp. 2024, 2025
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terracotta.exception;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mscott
 */
public class RuntimeEntityExceptionTest {
  
  public RuntimeEntityExceptionTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getClassName method, of class RuntimeEntityException.
   */
  @Test
  public void testEntityInfoNotProvided() {
    String description = "this is a test";
    RuntimeEntityExceptionImpl impl = new RuntimeEntityExceptionImpl(null, null, description);
    Assert.assertEquals(description, impl.getMessage());
    Assert.assertEquals(description, impl.getDescription());
  }

    /**
   * Test of getClassName method, of class RuntimeEntityException.
   */
  @Test
  public void testEntityInfoProvided() {
    String description = "this is a test";
    String clz = "org.terracotta.SuperEntity";
    String name =  "test";
    RuntimeEntityExceptionImpl impl = new RuntimeEntityExceptionImpl(clz, name, description);
    Assert.assertNotEquals(description, impl.getMessage());
    Assert.assertEquals(clz, impl.getClassName());
    Assert.assertEquals(name, impl.getEntityName());
    Assert.assertTrue(impl.getMessage().contains(clz));
    Assert.assertTrue(impl.getMessage().contains(name));
  }

  public class RuntimeEntityExceptionImpl extends RuntimeEntityException {

    public RuntimeEntityExceptionImpl(String clz, String name, String description) {
      super(clz, name, description, null);
    }
  }
  
}
