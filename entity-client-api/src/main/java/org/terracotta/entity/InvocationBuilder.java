package org.terracotta.entity;

import java.util.concurrent.Future;

/**
 * @author twu
 */
public interface InvocationBuilder {

  InvocationBuilder returnsValue(boolean returnsValue);

  InvocationBuilder payload(byte[] payload);

  Future<?> invoke();
}
