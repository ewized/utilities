package com.ewized.utilities.bukkit.util;

import com.ewized.utilities.core.util.locale.LocaleUtil;
import com.ewized.utilities.core.util.locale.LocaleWrapper;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkNotNull;

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
                throw new Exception();
            }

            Method method = Player.class.getMethod("getLocale");
            method.setAccessible(true);
            this.locale = (String) method.invoke(player);
        } catch (Exception e) {
            this.locale = DEFAULT_LOCALE;
        }
    }

    /** Translate to the specific locale with formatting */
    public String get(String key, Object... args) {
        checkNotNull(player);

        return super.get(key, args);
    }
}
