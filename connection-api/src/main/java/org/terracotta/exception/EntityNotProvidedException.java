package org.terracotta.exception;


/**
 * This specific EntityException type is thrown in cases where an attempt was made to try to create an entity but no provider
 * existed for the requested entity class.
 */
public class EntityNotProvidedException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityNotProvidedException(String className, String entityName) {
    super(className, entityName, "no provider found for class");
  }
}
