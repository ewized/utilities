package com.ewized.utilities.bukkit.util;

import com.ewized.utilities.core.util.locale.LocaleManager;
import com.ewized.utilities.core.util.locale.LocaleUtil;
import org.bukkit.entity.Player;

import static com.ewized.utilities.bukkit.util.MessageUtil.replaceColors;

@SuppressWarnings("unused")
public abstract class BukkitLocale implements LocaleUtil {
    private Player player;
    private String locale;
    private LocaleManager localeManager;

    /** Start creating locales for the specific player's locale */
    public BukkitLocale(Player player) {
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
