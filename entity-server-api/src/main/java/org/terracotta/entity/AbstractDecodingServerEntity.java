package org.terracotta.entity;

/**
 * @author twu
 */
public abstract class AbstractDecodingServerEntity<I, O> implements ServerEntity {

  protected abstract I decodeInput(byte[] bytes);

  protected abstract byte[] encodeOutput(O o);

  protected abstract O invoke(SourceID sourceID, I input);

  @Override
  public final byte[] invoke(SourceID sourceID, byte[] arg) {
    return encodeOutput(invoke(sourceID, decodeInput(arg)));
  }
}
