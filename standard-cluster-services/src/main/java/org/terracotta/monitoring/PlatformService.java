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
    /**
     * Query basic server information across the stripe.
     * 
     * Gives basic information about the stripe's servers.
     * 
     * @return an array of currently running server information
     */
    PlatformServer[] queryServers();
    /**
     * Query basic information about entities created on a particular server.
     * 
     *
     * @param server
     * @return an array of basic entity information created on the server.
     */
    PlatformEntity[] queryEntities(PlatformServer server);
    /**
     * Verify that an entity is consistently created across the entire stripe
     * 
     * @param type the type identifier of the entity
     * @param name the name of the entity
     * @return true if the entity is consistent and available across the stripe
     */
    boolean verifyEntity(String type, String name);
}
