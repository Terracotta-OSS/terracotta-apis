/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.terracotta.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mscott
 */
public class RuntimeEntityExceptionTest {
  
  public RuntimeEntityExceptionTest() {
  }
  
  @BeforeAll
  public static void setUpClass() {
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
  
  @BeforeEach
  public void setUp() {
  }
  
  @AfterEach
  public void tearDown() {
  }

  /**
   * Test of getClassName method, of class RuntimeEntityException.
   */
  @Test
  public void testEntityInfoNotProvided() {
    String description = "this is a test";
    RuntimeEntityExceptionImpl impl = new RuntimeEntityExceptionImpl(null, null, description);
    Assertions.assertEquals(description, impl.getMessage());
    Assertions.assertEquals(description, impl.getDescription());
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
    Assertions.assertNotEquals(description, impl.getMessage());
    Assertions.assertEquals(clz, impl.getClassName());
    Assertions.assertEquals(name, impl.getEntityName());
    Assertions.assertTrue(impl.getMessage().contains(clz));
    Assertions.assertTrue(impl.getMessage().contains(name));
  }

  public class RuntimeEntityExceptionImpl extends RuntimeEntityException {

    public RuntimeEntityExceptionImpl(String clz, String name, String description) {
      super(clz, name, description, null);
    }
  }
  
}
