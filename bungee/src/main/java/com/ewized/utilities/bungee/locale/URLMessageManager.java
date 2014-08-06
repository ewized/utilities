package com.ewized.utilities.bungee.locale;

import com.ewized.utilities.bungee.Utilities;
import com.ewized.utilities.cache.QuickCache;
import com.ewized.utilities.locale.URLLocaleManager;

class URLMessageManager extends URLLocaleManager {
    private static QuickCache<URLMessageManager> inst = QuickCache.builder(URLMessageManager.class).build();
    private static final String LOCALE_URL = "https://git.year4000.net/year4000/locales/raw/master/com/ewized/utilities/locales/";

    public URLMessageManager() {
        super(Utilities.getInst().getLog(), LOCALE_URL, parseJson(LOCALE_URL + LOCALES_JSON));
    }

    static URLMessageManager get() {
        return inst.get();
    }
}
