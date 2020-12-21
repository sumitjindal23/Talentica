package org.example.spring.inventory.config;

import org.example.spring.inventory.resource.InventoryResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;

@Named
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(InventoryResource.class);
    }
}
