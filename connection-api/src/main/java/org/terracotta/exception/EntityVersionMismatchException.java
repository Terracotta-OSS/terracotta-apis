package org.terracotta.exception;



public class EntityVersionMismatchException extends EntityException {
  private static final long serialVersionUID = 1L;
  
  public EntityVersionMismatchException(String className, String entityName, long expectedVersion, long attemptedVersion) {
    super(className, entityName, "version mismatch (expected " + expectedVersion + " but attempted " + attemptedVersion + ")");
  }
}
