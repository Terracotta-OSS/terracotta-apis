package org.terracotta.exception;


/**
 * This specific EntityException type is thrown in cases where an entity failed to be fetched or destroyed because no entity
 * exists with the given class and name.
 */
public class EntityNotFoundException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityNotFoundException(String className, String entityName) {
    super(className, entityName, "not found");
  }
}
