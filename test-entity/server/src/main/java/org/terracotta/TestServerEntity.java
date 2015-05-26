package org.terracotta;

import org.terracotta.entity.ClientDescriptor;
import org.terracotta.entity.ConcurrencyStrategy;
import org.terracotta.entity.NoConcurrencyStrategy;
import org.terracotta.entity.ActiveServerEntity;

/**
 * @author twu
 */
public class TestServerEntity implements ActiveServerEntity {
  @Override
  public ConcurrencyStrategy getConcurrencyStrategy() {
    return new NoConcurrencyStrategy();
  }

  @Override
  public void connected(ClientDescriptor clientDescriptor) {
  }

  @Override
  public void disconnected(ClientDescriptor clientDescriptor) {
  }

  @Override
  public byte[] getConfig() {
    return new byte[0];
  }

  @Override
  public byte[] invoke(ClientDescriptor clientDescriptor, byte[] arg) {
    return arg;
  }

  @Override
  public void createNew() {
  }

  @Override
  public void loadExisting() {
  }

  @Override
  public void destroy() {
  }
}
