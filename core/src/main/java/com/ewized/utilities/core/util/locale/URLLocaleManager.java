package com.ewized.utilities.core.util.locale;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@NoArgsConstructor
@SuppressWarnings("unused")
public class URLLocaleManager extends AbstractLocaleManager {
    public static final String LOCALES_JSON = "locales.json";
    private static final Gson gson = new Gson();

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

    /**
     * Creates an string array based on the provided url.
     * @param url The url that is a json object
     * @return String[] of locale codes, or default to DEFAULT_LOCALE
     */
    public static String[] parseJson(String url) {
        try {
            return gson.fromJson(new InputStreamReader(new URL(url).openStream()), String[].class);
        } catch (IOException e) {
            return new String[] {DEFAULT_LOCALE};
        }
    }
}
