package org.terracotta.entity;

// [sent, received, result*, [failure], complete, retired] == server side sequence
// [failure, complete, retired] == client side failure sequence
public interface InvocationCallback<R> {

  default void sent() {}

  /**
   *
   */
  default void received() {}

  default void result(R response) {}

  default void failure(Throwable failure) {}

  /**
   * The responses to this message are a complete set, no more response (success or failure will be sent)
   */
  default void complete() {}

  /**
   * The result of this message has been fully duplicated... no more callbacks will happen
   */
  default void retired() {}
}
