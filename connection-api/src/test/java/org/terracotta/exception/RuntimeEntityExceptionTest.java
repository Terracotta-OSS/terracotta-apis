/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
