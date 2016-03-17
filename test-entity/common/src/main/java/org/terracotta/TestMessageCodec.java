package org.terracotta;

import org.terracotta.entity.MessageCodec;
import org.terracotta.entity.MessageCodecException;

/**
 * @author vmad
 */
public class TestMessageCodec implements MessageCodec<TestMessage, TestResponse> {
    @Override
    public byte[] encodeMessage(TestMessage message) throws MessageCodecException {
        return message.getPayload();
    }

    @Override
    public TestMessage decodeMessage(byte[] payload) {
        return new TestMessage(payload);
    }

    @Override
    public byte[] encodeResponse(TestResponse response) throws MessageCodecException {
        return response.getPayload();
    }

    @Override
    public TestResponse decodeResponse(byte[] payload) throws MessageCodecException {
        return new TestResponse(payload);
    }
}
