package com.ewized.utilities;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public final class FileUtil {
    private FileUtil() {}

    /**
     * Copy one file source to a destination.
     * @param source The file source.
     * @param destination The new path.
     * @throws IOException
     */
    public static void copy(File source, File destination) throws IOException {
        copy(source, destination, false);
    }

    /**
     * Copy one file source to a destination.
     * @param source The file source.
     * @param destination The new path.
     * @param force Should this operation overwrite.
     * @throws IOException
     */
    public static void copy(File source, File destination, boolean force) throws IOException {
        if (!source.exists()) {
            throw new IllegalArgumentException("Source (" + source.getPath() + ") doesn't exist.");
        }

        if (!force && destination.exists()) {
            throw new IllegalArgumentException("Destination (" + destination.getPath() + ") exists.");
        }

        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }

    private static void copyDirectory(File source, File destination) throws IOException {
        if (!destination.mkdirs()) {
            throw new IOException("Failed to create destination directories");
        }

        File[] files = source.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                copyDirectory(file, new File(destination, file.getName()));
            } else {
                copyFile(file, new File(destination, file.getName()));
            }
        }
    }

    private static void copyFile(File source, File destination) throws IOException {
        Files.copy(source, destination);
    }

    /** Delete the given file or directory */
    public static void delete(File f) {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }

        f.delete();
    }

    /** Is the given file hidden */
    public static boolean isHidden(File file) {
        return file.isHidden() || file.getName().startsWith(".");
    }
}
