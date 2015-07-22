package org.terracotta.entity;

/**
 * Service Provider configuration which will be used to initialize service providers.
 */
public interface ServiceProviderConfiguration {
  Class<? extends ServiceProvider> getServiceProviderType();
}
