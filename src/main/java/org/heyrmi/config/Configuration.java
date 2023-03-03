package org.heyrmi.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.LoadType;

@Config.LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/test/resources/config.properties"
})
public interface Configuration extends Config {

    @DefaultValue("selenoid")
    String runmode();

    @DefaultValue("http://localhost:4444/wd/hub")
    String girdURL();

    @DefaultValue("no")
    String sendallureresults();
}
