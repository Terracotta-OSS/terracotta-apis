package org.terracotta;

import org.terracotta.entity.EntityMessage;

/**
 * @author vmad
 */
public class TestMessage implements EntityMessage {
    private final byte[] payload;

    public TestMessage(byte[] payload) {
        this.payload = payload;
    }

    public byte[] getPayload() {
        return this.payload;
    }
}
