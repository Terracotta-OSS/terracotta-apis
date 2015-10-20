package org.terracotta.exception;


/**
 * This specific EntityException type is thrown in cases where an entity couldn't be created or fetched because the client
 * tried to access it using an incompatible entity version.
 * In the case of creation, this does still imply that an entity with that class and name otherwise could be created.
 * In the case of a fetch, this does imply that an entity with that class and name does exist.
 */
public class EntityVersionMismatchException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityVersionMismatchException(String className, String entityName, long expectedVersion, long attemptedVersion) {
    super(className, entityName, "version mismatch (expected " + expectedVersion + " but attempted " + attemptedVersion + ")");
  }
}
