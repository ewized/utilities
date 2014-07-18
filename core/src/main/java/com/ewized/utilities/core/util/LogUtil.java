package com.ewized.utilities.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@AllArgsConstructor
@SuppressWarnings("unused")
public class LogUtil {
    protected Logger logger;
    protected Level level;
    protected boolean debug;

    public LogUtil() {
        this(Logger.getLogger(LogUtil.class.getName()));
    }

    public LogUtil(Logger logger) {
        this(logger, Level.INFO, Boolean.valueOf(System.getProperty("debug")));
    }

    public LogUtil(Logger logger, boolean debug) {
        this(logger, Level.INFO, debug);
    }

    public LogUtil(Logger logger, Level level) {
        this(logger, level, Boolean.valueOf(System.getProperty("debug")));
    }

    /** Logs a message to the console */
    public void log(String message, Object... args) {
        logger.log(level, String.format(MessageUtil.stripColors(message), args));
    }

    /** Logs a debug message to the console */
    public void debug(String message, Object... args) {
        if (debug) {
            Level old = level;
            setLevel(Level.WARNING);
            log("DEBUG: " + MessageUtil.stripColors(message), args);
            setLevel(old);
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
