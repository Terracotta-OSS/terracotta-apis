package org.terracotta.entity;

/**
 *
 * API for exposing platform configuration
 *
 * @author vmad
 */
public interface PlatformConfiguration {
  /**
   * Gets configured name for this server
   *
   * @return configured server name
   */
  String getServerName();
}
