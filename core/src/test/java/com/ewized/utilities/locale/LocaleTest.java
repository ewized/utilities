package com.ewized.utilities.locale;

import junit.framework.Assert;
import lombok.extern.java.Log;
import org.junit.Test;

import java.io.File;
import java.net.URL;

@Log
public class LocaleTest {
    @Test
    public void localeTests() throws Exception{
        test(new ClassLocaleManager(System.getProperty("test.locales.class"), "en_US", "fr_FR"));

        test(new ClassLocaleManager(LocaleTest.class, System.getProperty("test.locales.class"), "en_US", "fr_FR"));

        test(new URLLocaleManager(System.getProperty("test.locales.url"), "en_US", "pt_BR"));

        test(new URLLocaleManager(new URL(System.getProperty("test.locales.url")), "en_US", "pt_BR"));

        test(new FileLocaleManager(new File(System.getProperty("test.locales.file")), "en_US", "fr_FR"));

        test(new FileLocaleManager(System.getProperty("test.locales.file"), "en_US", "fr_FR"));

    }

    private static void test(AbstractLocaleManager manager) {
        Assert.assertTrue(manager.getLocales().size() > 0);

        manager.getLocales().forEach((code, property) -> {
            LocaleUtil locale = (key, args) -> String.format(property.getProperty(key), args);
            Assert.assertEquals(locale.get("locale.code").toLowerCase(), code.toString());
        });
    }
}
