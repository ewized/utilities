package com.ewized.utilities.core.message;

import com.ewized.utilities.core.util.locale.LocaleManager;

public class MessageManager extends LocaleManager {
    private static MessageManager inst;
    private static String[] codes = {"en_US", "en_PT", "pt_PT", "pt_BR"};

    private MessageManager() {
        super(MessageManager.class);
    }

    public static MessageManager get() {
        if (inst == null) {
            inst = new MessageManager();
        }

        return inst;
    }

    @Override
    protected void loadLocales(String path) {
        for (String locale : codes) {
            loadLocale(locale, clazz.getResourceAsStream(LOCALE_PATH + locale + ".properties"));
        }
    }
}
