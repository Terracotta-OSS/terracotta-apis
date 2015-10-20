package org.terracotta.exception;


/**
 * This specific EntityException type is thrown in cases where an entity failed to be created because an entity with that
 * class and name already exists.
 */
public class EntityAlreadyExistsException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityAlreadyExistsException(String className, String entityName) {
    super(className, entityName, "already exists");
  }
}
