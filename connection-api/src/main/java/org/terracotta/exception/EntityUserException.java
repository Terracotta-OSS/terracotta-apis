package org.terracotta.exception;


/**
 * This specific EntityException type is thrown in cases where an unexpected exception was thrown from user code associated
 * with the entity.
 */
public class EntityUserException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityUserException(String className, String entityName, Exception e) {
    super(className, entityName, "exception in user code: " + e.getLocalizedMessage());
  }
}
