package com.ewized.utilities.bukkit;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Utilities extends JavaPlugin {
    @Getter
    private static Utilities instance;

    public Utilities() {
        instance = this;
    }
}
