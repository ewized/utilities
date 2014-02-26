package com.ewized.utilities.bukkit.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.material.Colorable;

import java.util.InputMismatchException;
import java.util.List;


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
}
