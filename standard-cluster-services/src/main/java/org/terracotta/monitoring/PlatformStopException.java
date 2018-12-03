package org.terracotta.monitoring;

public class PlatformStopException extends Exception {
  private static final long serialVersionUID = 7404140788018130043L;

  public PlatformStopException(String message) {
    super(message);
  }

  public PlatformStopException(String message, Throwable cause) {
    super(message, cause);
  }
}
