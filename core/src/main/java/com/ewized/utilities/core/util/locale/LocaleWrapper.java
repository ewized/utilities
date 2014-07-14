package com.ewized.utilities.core.util.locale;

import com.google.common.base.Joiner;
import lombok.Getter;

import static com.ewized.utilities.core.util.MessageUtil.message;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract layout to add simple translation keys.
 * To get started with this you must populate
 * the LocaleManager with your own manager. Then
 * override the constructor to add the manager when
 * this player is loaded.
 */
public abstract class LocaleWrapper implements LocaleUtil {
    protected LocaleManager localeManager;
    @Getter
    protected String locale;

    /** Translate to the specific locale with formatting */
    public String get(String key, Object... args) {
        checkNotNull(locale);
        checkNotNull(localeManager);

        if (!localeManager.isLocale(locale)) {
            return key + "|" + Joiner.on(", ").join(args);
        }

        return message(localeManager.getLocale(locale).getProperty(key, key), args);
    }

}
