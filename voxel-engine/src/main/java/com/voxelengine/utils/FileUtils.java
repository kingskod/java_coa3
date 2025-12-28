package com.voxelengine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * Utility class for File I/O operations.
 * Provides methods to load resources from the classpath or filesystem and manage directories.
 */
public class FileUtils {

    /**
     * Loads a file from the classpath as a String.
     * Fallback: Checks the local file system (src/main/resources) if not found in classpath.
     *
     * @param path The path to the resource.
     * @return The content of the resource as a String.
     * @throws RuntimeException If the resource cannot be found or read.
     */
    public static String loadResourceAsString(String path) {
        InputStream is = null;
        
        // 1. Try Classpath (Standard method)
        // Ensure path starts with / for root lookup
        String classpathPath = path.startsWith("/") ? path : "/" + path;
        is = FileUtils.class.getResourceAsStream(classpathPath);

        // 2. Try Filesystem (Fallback for First-Run / Dev Environment)
        // If the asset was just generated, it exists in 'src' but not yet in 'build'
        if (is == null) {
            try {
                // Try looking in the standard resource directory
                File f = new File("src/main/resources/" + (path.startsWith("/") ? path.substring(1) : path));
                if (f.exists()) {
                    is = new FileInputStream(f);
                }
            } catch (IOException e) {
                // Ignore, throw exception below
            }
        }

        if (is == null) {
            throw new RuntimeException("Resource not found: " + path + " (Checked Classpath and Filesystem)");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + path, e);
        }
    }

    /**
     * Ensures that a directory exists, creating it if necessary.
     *
     * @param path The path of the directory.
     * @throws RuntimeException If the directory cannot be created.
     */
    public static void ensureDirectory(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create directory: " + path, e);
            }
        }
    }
}