package com.ewized.utilities.bukkit.util;

import com.ewized.utilities.core.util.locale.LocaleUtil;
import com.ewized.utilities.core.util.locale.LocaleWrapper;
import org.bukkit.entity.Player;

import static com.google.common.base.Preconditions.checkNotNull;

@SuppressWarnings("unused")
public abstract class BukkitLocale extends LocaleWrapper implements LocaleUtil {
    private Player player;

    /** Start creating locales for the specific player's locale */
    public BukkitLocale(Player player) {
        this.player = player;
        // TODO this.locale = player.getLocale();
    }

    /** Translate to the specific locale with formatting */
    public String get(String key, Object... args) {
        checkNotNull(player);

        return super.get(key, args);
    }
}
