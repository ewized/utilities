package com.ewized.utilities.bungee;

import lombok.Getter;

public class Utilities extends BungeePlugin {
    @Getter
    private static Utilities inst;

    @Override
    public void onLoad() {
        inst = this;
    }
}
