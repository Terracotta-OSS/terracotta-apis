package org.terracotta.exception;



public class EntityUserException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityUserException(String className, String entityName, Exception e) {
    super(className, entityName, "exception in user code: " + e.getLocalizedMessage());
  }
}
