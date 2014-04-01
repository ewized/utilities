package com.ewized.utilities.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

@SuppressWarnings("unused")
public class LocationUtil {
    /**
     * Create a location
     * @param world The world.
     * @param x The x cord.
     * @param y The y cord.
     * @param z The z cord.
     * @return Location
     */
    public static Location create(World world, int x, int y, int z) {
        return create(world, (double) x, (double) y, (double) z);
    }

    /**
     * Create a location
     * @param world The world.
     * @param x The x cord.
     * @param y The y cord.
     * @param z The z cord.
     * @return Location
     */
    public static Location create(World world, double x, double y, double z) {
        return new Location(world, x, y, z);
    }

    /**
     * Make a location centered
     * @param location The location to make centered.
     * @return The centered location.
     */
    public static Location center(Location location) {
        location.setX(location.getX() + 0.5);
        location.setY(location.getY() + 0.5);
        location.setZ(location.getZ() + 0.5);
        return location;
    }

    /**
     * Make a location centered
     * @param block The location to make centered.
     * @return The centered location.
     */
    public static Location center(Block block) {
        return center(block.getLocation());
    }

    /**
     * Parse a string to a given location.
     * @param world The world.
     * @param loc The string to parse.
     * @param regex The split regex key.
     * @return Parsed location.
     */
    public static Location parseLocation(World world, String loc, String regex) {
        String[] cords = loc.split(regex);
        return create(
                world,
                Double.valueOf(cords[0]),
                Double.valueOf(cords[1]),
                Double.valueOf(cords[2])
        );
    }

    /**
     * Parse a string to a given location.
     * @param world The world.
     * @param loc The string to parse.
     * @return Parsed location.
     */
    public static Location parseLocation(World world, String loc) {
        return parseLocation(world, loc, ":");
    }

    /**
     * Parse a string to a given location.
     * @param loc The string to parse.
     * @param regex The split regex key.
     * @return Parsed location.
     */
    public static Location parseLocation(String loc, String regex) {
        String[] cords = loc.split(regex);
        return parseLocation(Bukkit.getWorld(cords[0]), cords[1]);
    }

    /**
     * Parse a string to a given location.
     * @param loc The string to parse.
     * @return Parsed location.
     */
    public static Location parseLocation(String loc) {
        return parseLocation(loc, "\\|");
    }
}
