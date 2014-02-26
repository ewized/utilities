package com.ewized.utilities.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageUtil extends com.ewized.utilities.core.util.MessageUtil {

    /**
     * Sends out a raw broadcast message.
     * @param message The message to broadcast.
     */
    public static void broadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    /**
     * Sends out a color broadcast message.
     * @param message The message to broadcast.
     */
    public static void colorBroadcast(String message) {
        broadcast(com.ewized.utilities.core.util.MessageUtil.replaceColors(message));
    }

    /**
     * Sends out a raw broadcast message.
     * @param message The message to broadcast.
     */
    public static void rawBroadcast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendRawMessage(message);
        }
    }
}
