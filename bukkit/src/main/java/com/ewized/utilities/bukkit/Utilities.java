package com.ewized.utilities.bukkit;

import lombok.Getter;

public class Utilities extends BukkitPlugin {
    @Getter
    private static Utilities inst;

    @Override
    public void onLoad() {
        inst = this;
    }
}
