package org.minctory.api.utils.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinctoryLogger {

    public static final Logger LOGGER_MAIN = LogManager.getLogger("Minctory");
    public static final Logger LOGGER_DEV = LogManager.getLogger("Minctory Develop Mode");

}
