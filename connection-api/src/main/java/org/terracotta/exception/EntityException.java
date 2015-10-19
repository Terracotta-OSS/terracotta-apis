package org.terracotta.exception;


public abstract class EntityException extends Exception {
  private static final long serialVersionUID = 1L;
  
  protected EntityException(String className, String entityName, String description) {
    super("Entity: " + className + ":" + entityName + " " + description);
  }
}
