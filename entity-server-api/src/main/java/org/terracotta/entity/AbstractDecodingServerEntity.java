package org.terracotta.entity;

/**
 * @author twu
 */
public abstract class AbstractDecodingServerEntity<I, O> implements ServerEntity {

  protected abstract I decodeInput(byte[] bytes);

  protected abstract byte[] encodeOutput(O o);

  protected abstract O invoke(ClientDescriptor clientDescriptor, I input);

  @Override
  public final byte[] invoke(ClientDescriptor clientDescriptor, byte[] arg) {
    return encodeOutput(invoke(clientDescriptor, decodeInput(arg)));
  }
}
