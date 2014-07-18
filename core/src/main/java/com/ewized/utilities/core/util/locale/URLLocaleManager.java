package com.ewized.utilities.core.util.locale;

import lombok.NoArgsConstructor;

import java.net.URL;

@NoArgsConstructor
public class URLLocaleManager extends AbstractLocaleManager {
    /**
     * Load the locale files once created.
     * @param url The url object the use.
     * @param locales The locale codes.
     */
    public URLLocaleManager(URL url, String... locales) {
        super(url.toString(), locales);
    }

    /**
     * Load the locale files once created.
     * @param path The url path ending with a slash.
     * @param locales The locale codes.
     */
    public URLLocaleManager(String path, String... locales) {
        super(path, locales);
    }

    /** Load all the locales from the url location. */
    protected void loadLocales() {
        for (String code : codes) {
            try {
                URL uri = new URL(path + code + EXTENSION);

                loadLocale(code, uri.openStream());
            } catch (Exception e) {
                log.log(e, false);
            }
        }
    }
}
