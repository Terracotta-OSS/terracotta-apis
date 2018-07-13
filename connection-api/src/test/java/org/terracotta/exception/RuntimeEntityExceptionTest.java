/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.terracotta.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
    assertEquals(description, impl.getMessage());
    assertEquals(description, impl.getDescription());
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
    assertNotEquals(description, impl.getMessage());
    assertEquals(clz, impl.getClassName());
    assertEquals(name, impl.getEntityName());
    assertTrue(impl.getMessage().contains(clz));
    assertTrue(impl.getMessage().contains(name));
  }

  public class RuntimeEntityExceptionImpl extends RuntimeEntityException {

    public RuntimeEntityExceptionImpl(String clz, String name, String description) {
      super(clz, name, description, null);
    }
  }
  
}
