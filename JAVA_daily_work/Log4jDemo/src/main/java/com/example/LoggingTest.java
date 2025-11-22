package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTest {

    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    public static void main(String[] args) {

        logger.trace("Trace Message — Verbose details for debugging");
        logger.debug("Debug Message — For developer analysis");
        logger.info("Info Message — Normal flow information");
        logger.warn("Warning Message — Something might be off");
        logger.error("Error Message — Something failed");
        logger.fatal("Fatal Message — Critical system failure");

        try {
            int i = 10 / 0;
        } catch (Exception ex) {
            logger.error("Division error: " + ex.getMessage());
        }

        try {
            String name = null;
            name.toLowerCase();
        } catch (Exception ex) {
            logger.error("NullPointer error: " + ex.getMessage());
        }

        logger.debug("End of main method");
    }
}
