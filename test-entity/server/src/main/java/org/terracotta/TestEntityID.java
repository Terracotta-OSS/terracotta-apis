package org.terracotta;

/**
 * Unique way to identify a server entity
 */
public class TestEntityID {

  private final String name;

  public TestEntityID(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

}
