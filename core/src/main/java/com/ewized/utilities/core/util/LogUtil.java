package com.ewized.utilities.core.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class LogUtil {
    protected Logger log = null;
    @Setter
    protected boolean debug = false;

    /** Logs a message to the console */
    public void log(String message, Object... args) {
        checkNotNull(log);

        log.log(Level.INFO, String.format(MessageUtil.stripColors(message), args));
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
