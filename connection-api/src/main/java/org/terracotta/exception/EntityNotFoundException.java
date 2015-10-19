package org.terracotta.exception;



public class EntityNotFoundException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityNotFoundException(String className, String entityName) {
    super(className, entityName, "not found");
  }
}
