package com.ewized.utilities.bungee.util;

import com.ewized.utilities.core.util.locale.LocaleManager;
import com.ewized.utilities.core.util.locale.LocaleUtil;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import static com.ewized.utilities.core.util.MessageUtil.replaceColors;

@SuppressWarnings("unused")
public abstract class BungeeLocale implements LocaleUtil {
    private ProxiedPlayer player;
    private String locale;
    private LocaleManager localeManager;

    /** Start creating locales for the specific player's locale */
    public BungeeLocale(ProxiedPlayer player) {
        this.player = player;
    }

    /** Translate to the specific locale with formatting */
    public String translate(String key, Object... args) {
        return replaceColors(String.format(
            localeManager.getLocale(locale).getProperty(key),
            args
        ));
    }
}
