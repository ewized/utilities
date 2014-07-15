package com.ewized.utilities.core.locale;

import com.ewized.utilities.core.util.locale.LocaleUtil;
import lombok.extern.java.Log;

import java.util.Properties;

@Log
public class LocaleTest {
    @org.junit.Test
    public void test() {
        LocaleTestManager manager = new LocaleTestManager();

        log.info("SIZE: " + manager.getLocales().size());

        for (Properties a : manager.getLocales().values()) {
            LocaleUtil locale = (key, args) -> String.format(a.getProperty(key), args);
            log.info(locale.get("how.are.you"));
        }
    }
}
