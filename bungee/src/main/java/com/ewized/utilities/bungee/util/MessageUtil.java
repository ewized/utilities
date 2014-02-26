package com.ewized.utilities.bungee.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;

public class MessageUtil extends com.ewized.utilities.core.util.MessageUtil {

    /**
     * Sends out a broadcast message.
     * @param message The message to broadcast.
     */
    public static void broadcast(String message) {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            player.sendMessage(makeMessage(message));
        }
    }

    /**
     * Sends out a broadcast message.
     * @param message The message to broadcast.
     */
    public static void broadcast(BaseComponent[] message) {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
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
     * Translate a normal string to a BaseComponent.
     * @param message The message to translate.
     * @return The translated message.
     */
    public static BaseComponent[] makeMessage(String message) {
        return TextComponent.fromLegacyText(message);
    }

    /**
     * Parse a message to be used.
     * @param message The message.
     * @return The parsed message.
     * @throws java.lang.Exception
     */
    public static BaseComponent[] parseMessage(String message) throws Exception {
        try {
            // Raw Message
            if (message.startsWith("{text:") || message.startsWith("{text:", 1)) {
                return ComponentSerializer.parse(message);
            }
            else {
                throw new Exception("Not a raw message.");
            }
        } catch (Exception e) {
            throw new Exception("Message could not be parsed.");
        }
    }

}
