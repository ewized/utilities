package com.ewized.utilities.bungee.util;

import com.ewized.utilities.core.util.locale.LocaleUtil;
import com.ewized.utilities.core.util.locale.LocaleWrapper;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("unused")
public abstract class BungeeLocale extends LocaleWrapper implements LocaleUtil {
    private ProxiedPlayer player;

    /** Start creating locales for the specific player's locale */
    public BungeeLocale(ProxiedPlayer player) {
        this.player = player;
        this.locale = player == null ? DEFAULT_LOCALE : player.getLocale().toString();
    }

    /** Translate to the specific locale with formatting */
    public String get(String key, Object... args) {
        return super.get(key, args);
    }
}
