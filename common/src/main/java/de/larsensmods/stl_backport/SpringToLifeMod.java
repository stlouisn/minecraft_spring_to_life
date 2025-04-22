package de.larsensmods.stl_backport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SpringToLifeMod {

    public static final String MOD_ID = "spring_to_life";

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringToLifeMod.class);

    public static void init() {
        LOGGER.info("SpringToLifeMod common init");
        LOGGER.info("SpringToLifeMod end common init");
    }
}
