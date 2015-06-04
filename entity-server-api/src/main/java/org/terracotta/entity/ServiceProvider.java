package org.terracotta.entity;

import org.terracotta.config.TcConfiguration;
import java.util.Optional;

/**
 * Service Provider which are provided at the platform level. Each of the service provisions its chunk of service by
 * in an appropriate configuration. The decision of namespacing a service is left upto the provider.
 *
 * @param <S> Type of service provided by the service provider
 * @param <SC> Type of Service COnfiguration which service accepts
 */
public interface ServiceProvider<S extends Service, SC extends ServiceConfiguration<S>> {

  /**
   * The platform configuration based on which the Service provider can choose to initalize itself.
   *
   * @param configuration platform configuration
   */
  void initialize(TcConfiguration configuration);

  /**
   * Get an instance of service from the provider.
   *
   * @param configuration Service configuratino which is to be used
   * @return service instance
   */
  Optional<S> getService(SC configuration);

  /**
   * Type of service which is provided by the service provider
   *
   * @return type of service
   */
  Class<S> getServiceType();

  /**
   * Destroys a given service instance.
   *
   * @param service instance to be destroyed
   */
  void destroy(S service);
}