package org.terracotta.entity;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * @author twu
 */
public interface InvocationBuilder {
  InvocationBuilder returnsValue(boolean returnsValue);

  InvocationBuilder payload(byte[] payload);

  Future<?> invoke();
}
