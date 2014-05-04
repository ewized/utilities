package com.ewized.utilities.core.util.locale;

import static com.google.common.base.Preconditions.checkNotNull;
import lombok.*;
import lombok.extern.java.Log;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * This abstract class is here for another class to implement
 * you will need to create a singleton pattern to use this
 * correctly.
 */
@Log
public abstract class LocaleManager {
    @Getter
    private Map<String, Properties> locales = new LinkedHashMap<>();
    @Getter
    private final Class clazz;
    private final String LOCALE_PATH = "/locales/";

    /**
     * Create a new instance of LocaleManager with the base class to use
     * and a specific locale path to use inside the resources.
     * @param clazz The class to be used.
     * @param localePath The path to find local files.
     */
    protected LocaleManager(Class clazz, String localePath) {
        this.clazz = clazz;

        // Run the loader with the specific path
        loadLocales(localePath);
    }

    /**
     * Create a new instance of LocaleManager with the base class to use
     * and a specific locale path to use inside the resources.
     * @param clazz The class to be used.
     */
    protected LocaleManager(Class clazz) {
        this.clazz = clazz;

        // Run the loader with the default path
        loadLocales(LOCALE_PATH);
    }

    /** The cycle to grab all locale properties in one swoop. */
    private void loadLocales(String path) {
        try {
            URL url = checkNotNull(clazz.getResource(path));

            File dir = new File(url.toURI());
            for (File file : dir.listFiles()) {
                String[] l = file.getName().split("\\.");

                loadLocale(
                    l[0],
                    clazz.getResourceAsStream(path + file.getName())
                );
            }
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
    }

    /**
     * Load a resource to be used, in the locale system.
     * @param key The name of the.
     */
    public void loadLocale(String key, InputStream locale) {
        Properties file = new Properties();
        try {
            file.load(locale);
            locales.put(key, file);
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    /**
     * Check is the requested locale is loaded.
     * @param locale The language code in string form.
     * @return true When the language is loaded.
     */
    public boolean isLocale(String locale) {
        return locales.containsKey(locale);
    }

    /**
     * Get the properties file for the current locale.
     * @param locale The locale code.
     * @return The properties for the locale.
     */
    public Properties getLocale(String locale) {
        return checkNotNull(locales.get(locale));
    }
}