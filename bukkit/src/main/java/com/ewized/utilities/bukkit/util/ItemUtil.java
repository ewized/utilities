package com.ewized.utilities.bukkit.util;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Colorable;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@SuppressWarnings("all")
public class ItemUtil {
    /**
     * Create a book with the data inside
     * @param title The title.
     * @param author The author.
     * @param pages The pages' content.
     * @return
     */
    public static ItemStack createBook(String title, String author, List<String> pages) {
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta)item.getItemMeta();

        meta.setTitle(title);
        meta.setAuthor(author);
        meta.setPages(pages);

        item.setItemMeta(meta);
        return item;
    }

    /**
     * Set the color of a item
     * @param item The item
     * @param color The color
     * @return Item with color
     * @throws InputMismatchException
     */
    public static ItemStack setColor(ItemStack item, DyeColor color) throws InputMismatchException {
        // Check is the item can have a color.
        if (!(item instanceof Colorable))
            throw new InputMismatchException("The item can not have a color.");

        ((Colorable) item).setColor(color);
        return item;
    }

    /**
     * Create a simple item.
     * @param item The name of the item.
     * @param amount The amount of items.
     * @param damage The damage of the item.
     * @return ItemStack
     */
    public static ItemStack makeItem(String item, int amount, short damage) {
        return new ItemStack(Material.valueOf(item.toUpperCase()), amount, damage);
    }

    /**
     * Create a simple item.
     * @param item The name of the item.
     * @param amount The amount of items.
     * @return ItemStack
     */
    public static ItemStack makeItem(String item, int amount) {
        return makeItem(item.toUpperCase(), amount, (short) 0);
    }

    /**
     * Create a simple item.
     * @param item The name of the item.
     * @return ItemStack
     */
    public static ItemStack makeItem(String item) {
        return makeItem(item.toUpperCase(), 1);
    }

    /**
     * Create a simple item.
     * @param item The name of the item.
     * @param nbt The nbt data of the item.
     * @return ItemStack
     */
    public static ItemStack makeItem(String item, String nbt) {
        ItemStack itemStack = makeItem(item.toUpperCase());
        itemStack.setItemMeta(addMeta(itemStack, nbt));
        return itemStack;
    }

    /**
     * Add ItemMeta to an item.
     * @param itemStack The item to use as a refrence point.
     * @param nbtJson The json string to make the item.
     * @return The ItemMeta.
     */
    public static ItemMeta addMeta(ItemStack itemStack, String nbtJson) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        Gson gson = new Gson();
        final Nbt nbt = gson.fromJson(nbtJson, Nbt.class);

        // Set the item's display
        if (nbt.getDisplay() != null) {
            if (nbt.getDisplay().getName() != null)
                itemMeta.setDisplayName(MessageUtil.replaceColors(nbt.getDisplay().getName()));
            // Set the item's lore
            if (nbt.getDisplay().getLore() != null) {
                itemMeta.setLore(new ArrayList<String>() {{
                    for (String line : nbt.getDisplay().getLore()) {
                        add(MessageUtil.replaceColors(line));
                    }
                }});
            }
            // Set the item's color if leather armor.
            if (nbt.getDisplay().getColor() != null) {
                if (itemMeta instanceof LeatherArmorMeta) {
                    DyeColor dyeColor = DyeColor.valueOf(nbt.getDisplay().getColor().toUpperCase());
                    ((LeatherArmorMeta) itemMeta).setColor(BukkitUtil.dyeColorToColor(dyeColor));
                }
            }
        }
        // Set the book's title author, and pages if its a book.
        if (nbt.getAuthor() != null && nbt.getTitle() != null && nbt.getPages() != null) {
            if (itemMeta instanceof BookMeta) {
                ((BookMeta) itemMeta).setAuthor(MessageUtil.replaceColors(nbt.getAuthor()));
                ((BookMeta) itemMeta).setTitle(MessageUtil.replaceColors(nbt.getTitle()));
                ((BookMeta) itemMeta).setPages(new ArrayList<String>() {{
                    for (String line : nbt.getPages()) {
                        add(MessageUtil.replaceColors(line));
                    }
                }});
            }
        }
        // Set the item's enchantment
        if (nbt.getEnchantments() != null) {
            for (Nbt.Enchantments enchantment: nbt.getEnchantments()) {
                // The true is forcing the item to have enchments even if the items can't have it.
                itemMeta.addEnchant(
                        Enchantment.getByName(enchantment.getName().toUpperCase()),
                        enchantment.getLevel(),
                        true
                );
            }
        }

        return itemMeta;
    }


    @Data
    @NoArgsConstructor
    /** NBT data of the item. */
    public class Nbt {
        /** Book Title. */
        private String title;
        /** Book Author. */
        private String author;
        /** Each array is a page in a book. */
        private String[] pages;
        /** The enchants the item will have. */
        private Enchantments[] enchantments;
        /** Control the display of the item. */
        private Display display;

        @Data
        @NoArgsConstructor
        /** The enchants the item will have. */
        public class Enchantments {
            /** Enchantment id number. */
            private String name;
            /** Enchantment level amount. */
            private int level;
        }

        @Data
        @NoArgsConstructor
        /** Control the display of the item. */
        public class Display {
            /** The name of the item. */
            private String name;
            /** The lore of the item, each array is a new line. */
            private String[] lore;
            /** The color of leather armor. */
            private String color;
        }
    }
}
