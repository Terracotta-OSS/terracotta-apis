package org.terracotta.monitoring;

import com.tc.classloader.CommonComponent;

/**
 *
 * A Service for entities to interact with Platform
 *
 *
 * @author vmad
 */
@CommonComponent
public interface PlatformService {

    /**
     * Dumps platform state into logs
     */
    void dumpPlatformState();

}
