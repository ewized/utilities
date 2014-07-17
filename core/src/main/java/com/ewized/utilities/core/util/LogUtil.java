package com.ewized.utilities.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.logging.Logger;

@Data
@AllArgsConstructor
@SuppressWarnings("unused")
public class LogUtil {
    protected Logger logger;
    protected boolean debug;

    public LogUtil() {
        this(Logger.getLogger(LogUtil.class.getName()), Boolean.parseBoolean(System.getProperty("debug")));
    }

    public LogUtil(Logger logger) {
        this(logger, false);
    }

    /** Logs a message to the console */
    public void log(String message, Object... args) {
        logger.info(String.format(MessageUtil.stripColors(message), args));
    }

    /** Logs a debug message to the console */
    public void debug(String message, Object... args) {
        if (debug) {
            log("DEBUG: " + MessageUtil.stripColors(message), args);
        }
    }

    /** Print out the stack trace */
    public void debug(Exception exception, boolean simple) {
        if (debug) {
            debug(exception.getMessage());

            if (!simple) {
                for (StackTraceElement element : exception.getStackTrace()) {
                    debug(element.toString());
                }
            }
        }
    }

    /** Print out the stack trace */
    public void log(Exception exception, boolean simple) {
        log(exception.getMessage());

        if (!simple) {
            for (StackTraceElement element : exception.getStackTrace()) {
                log(element.toString());
            }
        }
    }
}
