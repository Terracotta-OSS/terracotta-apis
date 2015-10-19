package org.terracotta.exception;


/**
 * The base class of the exception hierarchy used by the entity API.
 * On its own, doesn't convey the meaning of the exceptional circumstance.  To determine and handle these specific
 * situations, catch the more specific sub-class exceptions.
 */
public abstract class EntityException extends Exception {
  private static final long serialVersionUID = 1L;
  
  protected EntityException(String className, String entityName, String description) {
    super("Entity: " + className + ":" + entityName + " " + description);
  }
}
