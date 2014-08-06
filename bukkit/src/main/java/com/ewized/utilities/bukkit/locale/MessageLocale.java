package com.ewized.utilities.bukkit.locale;

import com.ewized.utilities.bukkit.BukkitLocale;
import com.ewized.utilities.bukkit.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class MessageLocale extends BukkitLocale {
    public MessageLocale(CommandSender sender) {
        super(sender instanceof Player ? (Player) sender : null);

        try {
            localeManager = URLMessageManager.get();

            if (localeManager.getLocales().size() == 0) {
                throw new Exception("URLMessageManager has 0 locales loaded.");
            }
        } catch (Exception e) {
            Utilities.log(e, true);
            localeManager = ClassMessageManager.get();
        }
    }
}
