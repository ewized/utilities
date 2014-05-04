package com.ewized.utilities.core.locale;

import com.ewized.utilities.core.util.locale.LocaleManager;
import com.ewized.utilities.core.util.locale.LocaleUtil;
import lombok.extern.java.Log;

import java.util.Properties;

@Log
public class LocaleTest {
    @org.junit.Test
    public void test() {
        LocaleManager manager = new LocaleManager(LocaleTest.class) {};

        log.info("SIZE: " + manager.getLocales().size());

        for (Properties a : manager.getLocales().values()) {
            LocaleUtil locale = (key, args) -> String.format(a.getProperty(key), args);
            log.info(locale.translate("how.are.you"));
        }
    }
}
