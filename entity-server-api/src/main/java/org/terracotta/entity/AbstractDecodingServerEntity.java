package org.terracotta.entity;

/**
 * @author twu
 */
public abstract class AbstractDecodingServerEntity<I, O> implements ServerEntity {

  protected abstract I decodeInput(byte[] bytes);

  protected abstract byte[] encodeOutput(O o);

  protected abstract O invoke(ClientID clientID, I input);

  @Override
  public final byte[] invoke(ClientID clientID, byte[] arg) {
    return encodeOutput(invoke(clientID, decodeInput(arg)));
  }
}
