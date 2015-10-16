package org.terracotta.exception;



public class EntityAlreadyExistsException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityAlreadyExistsException(String className, String entityName) {
    super(className, entityName, "already exists");
  }
}
