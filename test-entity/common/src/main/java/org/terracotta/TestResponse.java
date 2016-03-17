package org.terracotta;

import org.terracotta.entity.EntityResponse;

/**
 * @author vmad
 */
public class TestResponse implements EntityResponse {
    public final byte[] payload;

    public TestResponse(byte[] payload) {
        this.payload = payload;
    }

    public byte[] getPayload() {
        return payload;
    }
}
