package org.terracotta.exception;



public class EntityNotProvidedException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityNotProvidedException(String className, String entityName) {
    super(className, entityName, "no provider found for class");
  }
}
