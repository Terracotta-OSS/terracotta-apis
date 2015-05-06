package org.terracotta.entity;

/**
 * @author twu
 */
public interface EntityClientEndpoint extends AutoCloseable {

  byte[] getEntityConfiguration();

  void registerListener(EndpointListener listener);

  InvocationBuilder beginInvoke();
  
  /**
   * The instance will be unusable after this call.
   */
  @Override
  void close();
}
