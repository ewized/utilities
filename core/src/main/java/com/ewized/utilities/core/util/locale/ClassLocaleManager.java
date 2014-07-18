package com.ewized.utilities.core.util.locale;

import lombok.NoArgsConstructor;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;

@NoArgsConstructor
public class ClassLocaleManager extends AbstractLocaleManager {
    private Class clazz;

    /**
     * Load the manager using this class as a reference point.
     * @param path The path to use.
     * @param locales The locale codes to use.
     */
    @ParametersAreNonnullByDefault
    public ClassLocaleManager(String path, String... locales) {
        this(ClassLocaleManager.class, path, locales);
    }

    /**
     * Load the manager with resources inside the class.
     * @param clazz The class to use as a reference.
     * @param path The path to use.
     * @param locales The locale codes to use.
     */
    @ParametersAreNonnullByDefault
    public ClassLocaleManager(Class clazz, String path, String... locales) {
        this.clazz = checkNotNull(clazz);
        this.path = checkNotNull(path);
        this.codes = checkNotNull(locales);

        loadLocales();
    }


    /** Load all the locales that are in the folder */
    public void loadLocales() {
        for (String locale : codes) {
            loadLocale(locale, clazz.getResourceAsStream(path + locale + EXTENSION));
        }
    }
}
