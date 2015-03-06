package org.terracotta;

import java.nio.ByteBuffer;

/**
 * @author twu
 */
public class TestEntityConfig {
  private int secret;

  public int getSecret() {
    return secret;
  }

  public void setSecret(int secret) {
    this.secret = secret;
  }
  
  public byte[] toBytes() {
    return ByteBuffer.allocate(4).putInt(secret).array();
  }
  
  public static TestEntityConfig fromBytes(byte[] bytes) {
    TestEntityConfig config = new TestEntityConfig();
    config.setSecret(ByteBuffer.wrap(bytes).getInt());
    return config;
  }
}
