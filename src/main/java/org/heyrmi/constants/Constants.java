package org.heyrmi.constants;

import lombok.Getter;

public final class Constants {

    // To aoivd object creation
    private Constants() {
    }

    // FilePaths Constants
    private static final @Getter String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final @Getter String CONFIGFILEPATH = RESOURCESPATH + "/config.properties";
}
