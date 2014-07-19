package com.ewized.utilities.bungee.message;

import com.ewized.utilities.bungee.Utilities;
import com.ewized.utilities.core.util.cache.QuickCache;
import com.ewized.utilities.core.util.locale.URLLocaleManager;

public class URLMessageManager extends URLLocaleManager {
    private static QuickCache<URLMessageManager> inst = QuickCache.builder(URLMessageManager.class).build();
    private static final String LOCALE_URL = "https://git.year4000.net/year4000/locales/raw/master/com/ewized/utilities/locales/";

    public URLMessageManager() {
        super(Utilities.getInst().getLog(), LOCALE_URL, parseJson(LOCALE_URL + LOCALES_JSON));
    }

    public static URLMessageManager get() {
        return inst.get();
    }
}
