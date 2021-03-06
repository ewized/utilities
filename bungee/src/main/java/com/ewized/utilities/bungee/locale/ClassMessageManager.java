package com.ewized.utilities.bungee.locale;

import com.ewized.utilities.bungee.Utilities;
import com.ewized.utilities.locale.ClassLocaleManager;

class ClassMessageManager extends ClassLocaleManager {
    private static ClassMessageManager inst;

    private ClassMessageManager() {
        super(Utilities.getInst().getLog(), "/com/ewized/utilities/locales/", "en_US", "en_PT", "pt_PT", "pt_BR");
    }

    static ClassMessageManager get() {
        if (inst == null) {
            inst = new ClassMessageManager();
        }

        return inst;
    }
}
