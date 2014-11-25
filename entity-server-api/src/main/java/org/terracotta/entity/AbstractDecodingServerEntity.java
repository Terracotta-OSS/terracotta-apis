package org.terracotta.entity;

/**
 * @author twu
 */
public abstract class AbstractDecodingServerEntity<I, O> implements ServerEntity {

  protected abstract I decodeInput(byte[] bytes);

  protected abstract byte[] encodeOutput(O o);

  protected abstract O invoke(I input);

  @Override
  public final byte[] invoke(final byte[] arg) {
    return encodeOutput(invoke(decodeInput(arg)));
  }
}
