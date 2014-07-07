package com.ewized.utilities.core.util;

@SuppressWarnings("unused")
public class MessageUtil {

    /**
     * Creates a message with colors and allowed for formatted.
     * @param message The message to create.
     * @param args The optional args to format the message.
     * @return The message all prettied and formatted.
     */
    public static String message(String message, Object... args) {
        return replaceColors(String.format(message, args));
    }

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

    /**
     * Check is the message could be a raw message
     * @param message Message to check.
     * @return true|false
     */
    public static boolean isRawMessage(String message) {
        return message.startsWith("{text:") || message.startsWith("{text:", 1);
    }

    /**
     * Replace end of line symbol with a char.
     * @param message The message to be translated.
     * @return The translated message.
     */
    public static String endOfLine(String message) {
        return message.replaceAll("\\n", "\n");
    }
}