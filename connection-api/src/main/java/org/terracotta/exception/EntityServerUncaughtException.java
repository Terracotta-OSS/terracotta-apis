package org.terracotta.exception;

/**
 * A Runtime exception that wraps exception(s) thrown from Server entity implementations
 */
public class EntityServerUncaughtException extends RuntimeEntityException {
  /**
   * All of these exception types have a specific description and refer to a type and name pair.
   *
   * @param className   The name of the type of the entity
   * @param entityName  The name of the entity instance
   * @param description The description describing the specific problem
   * @param cause       The underlying cause of the exception, null if nothing appropriate
   */
  public EntityServerUncaughtException(final String className, final String entityName, final String description, final Throwable cause) {
    super(className, entityName, description, cause);
  }
}
