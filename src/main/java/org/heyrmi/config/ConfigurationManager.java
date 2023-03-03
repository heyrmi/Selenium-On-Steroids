package org.heyrmi.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    // To avoid object creation
    private ConfigurationManager() {
    }
     public static Configuration configuration() {
     return ConfigCache.getOrCreate(Configuration.class);
     }
}
