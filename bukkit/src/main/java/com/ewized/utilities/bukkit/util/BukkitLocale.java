package com.ewized.utilities.bukkit.util;

import com.ewized.utilities.core.util.locale.LocaleUtil;
import com.ewized.utilities.core.util.locale.LocaleWrapper;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public abstract class BukkitLocale extends LocaleWrapper implements LocaleUtil {
    private Player player;

    /** Start creating locales for the specific player's locale */
    public BukkitLocale(Player player) {
        this.player = player;
        //this.locale = DEFAULT_LOCALE;
        //this.locale = player == null ? DEFAULT_LOCALE : player.getLocale();

        try {
            if (player == null) {
                this.locale = DEFAULT_LOCALE;
                return;
            }

            Field field = Player.class.getField("locale");
            field.setAccessible(true);
            this.locale = field.toString();
        } catch (Exception e) {
            this.locale = DEFAULT_LOCALE;
        }
    }

    /** Translate to the specific locale with formatting */
    public String get(String key, Object... args) {
        return super.get(key, args);
    }
}
