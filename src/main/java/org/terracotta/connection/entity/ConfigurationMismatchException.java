package org.terracotta.connection.entity;

/**
 * @author twu
 */
public class ConfigurationMismatchException extends Exception {
  public ConfigurationMismatchException() {
  }

  public ConfigurationMismatchException(final String message) {
    super(message);
  }

  public ConfigurationMismatchException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ConfigurationMismatchException(final Throwable cause) {
    super(cause);
  }

  public ConfigurationMismatchException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
