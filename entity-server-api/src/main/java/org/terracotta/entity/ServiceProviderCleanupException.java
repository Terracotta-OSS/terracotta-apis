package org.terracotta.entity;

/**
 * @author vmad
 */
public class ServiceProviderCleanupException extends Exception {
    public ServiceProviderCleanupException(String s) {
        super(s);
    }

    public ServiceProviderCleanupException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
