package com.ewized.utilities.bukkit.message;

import com.ewized.utilities.bukkit.Utilities;
import com.ewized.utilities.core.util.locale.ClassLocaleManager;

public class MessageManager extends ClassLocaleManager {
    private static MessageManager inst;

    private MessageManager() {
        super(Utilities.getInst().getLog(), "/com/ewized/utilities/locales/", "en_US", "en_PT", "pt_PT", "pt_BR");
    }

    public static MessageManager get() {
        if (inst == null) {
            inst = new MessageManager();
        }

        return inst;
    }
}
