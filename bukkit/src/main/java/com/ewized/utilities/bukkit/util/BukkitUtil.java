package com.ewized.utilities.bukkit.util;

import org.bukkit.Color;
import org.bukkit.DyeColor;

import java.util.HashMap;

public class BukkitUtil {
    /** Color to DyeColor Map. */
    public static final HashMap<Color, DyeColor> colorToDyeColorMap = new HashMap<Color, DyeColor>() {{
        put(Color.AQUA, DyeColor.LIGHT_BLUE);
        put(Color.BLACK, DyeColor.BLACK);
        put(Color.BLUE, DyeColor.LIGHT_BLUE);
        put(Color.FUCHSIA, DyeColor.PINK);
        put(Color.GRAY, DyeColor.GRAY);
        put(Color.GREEN, DyeColor.GREEN);
        put(Color.LIME, DyeColor.LIME);
        put(Color.MAROON, DyeColor.MAGENTA);
        put(Color.NAVY, DyeColor.BLUE);
        put(Color.OLIVE, DyeColor.GREEN);
        put(Color.ORANGE, DyeColor.ORANGE);
        put(Color.PURPLE, DyeColor.PURPLE);
        put(Color.RED, DyeColor.RED);
        put(Color.SILVER, DyeColor.SILVER);
        put(Color.YELLOW, DyeColor.YELLOW);
        put(Color.WHITE, DyeColor.WHITE);
        put(Color.TEAL, DyeColor.LIGHT_BLUE);
    }};
    /** DyeColor to Color Map. */
    public static final HashMap<DyeColor, Color> dyeColorToColorMap = new HashMap<DyeColor, Color>() {{
        put(DyeColor.LIGHT_BLUE, Color.AQUA);
        put(DyeColor.BLACK, Color.BLACK);
        put(DyeColor.LIGHT_BLUE, Color.BLUE);
        put(DyeColor.PINK, Color.FUCHSIA);
        put(DyeColor.GRAY, Color.GRAY);
        put(DyeColor.GREEN, Color.GREEN);
        put(DyeColor.LIME, Color.LIME);
        put(DyeColor.MAGENTA, Color.MAROON);
        put(DyeColor.BLUE, Color.NAVY);
        put(DyeColor.GREEN, Color.OLIVE);
        put(DyeColor.ORANGE, Color.ORANGE);
        put(DyeColor.PURPLE, Color.PURPLE);
        put(DyeColor.RED, Color.RED);
        put(DyeColor.SILVER, Color.SILVER);
        put(DyeColor.YELLOW, Color.YELLOW);
        put(DyeColor.WHITE, Color.WHITE);
        put(DyeColor.LIGHT_BLUE, Color.TEAL);
    }};

    /**
     * Get the Color for the DyeColor match.
     * @param dyeColor The DyeColor.
     * @return The Color.
     */
    public static Color dyeColorToColor(DyeColor dyeColor) {
        return dyeColorToColorMap.get(dyeColor);
    }

    /**
     * Get the DyeColor for the Color match.
     * @param color The Color.
     * @return The DyeColor.
     */
    public static DyeColor colorToDyeColor(Color color) {
        return colorToDyeColorMap.get(color);
    }

}
