package org.terracotta.entity;

public abstract class AbstractDecodingServerEntityPassive<I> implements PassiveServerEntity {

  protected abstract I decodeInput(byte[] bytes);

  protected abstract void invoke(I input);

  @Override
  public final void invoke(byte[] arg) {
    invoke(decodeInput(arg));
  }
}
