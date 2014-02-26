package com.ewized.utilities.core.util;

public class MessageUtil {

    /**
     * Replace color codes with minecraft colors.
     * @param message The message to be translated.
     * @return The translated message.
     */
    public static String replaceColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Strip minecraft colors to create a clean message.
     * @param message The message to strip from.
     * @return The clean message with no colors.
     */
    public static String stripColors(String message) {
        return ChatColor.stripColor(message);
    }

    /**
     * Replace minecraft colors with color codes.
     * @param message The message to be translated.
     * @return The translated message.
     */
    public static String replaceRawColors(String message) {
        return message.replaceAll(ChatColor.COLOR_CHAR + "[0-9a-fA-Fk-rK-R]", "&%1");
    }

}