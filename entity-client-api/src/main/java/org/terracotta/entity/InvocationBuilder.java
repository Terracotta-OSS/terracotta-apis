package org.terracotta.entity;

import java.util.concurrent.Future;

/**
 * @author twu
 */
public interface InvocationBuilder {

  InvocationBuilder ackReceipt();
  
  InvocationBuilder ackReplicated();
  
  InvocationBuilder ackLogged();
  
  InvocationBuilder ackCompleted();
  
  InvocationBuilder payload(byte[] payload);

  Future<byte[]> invoke();
}
