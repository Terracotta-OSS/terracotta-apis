package org.terracotta.entity;

/**
 * An EntityUserException thrown from entity server invoke implementation will be propagated to the client entity implementation
 */
public class EntityUserException extends Exception {
  public EntityUserException(final String message) {
    super(message);
  }

  public EntityUserException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
