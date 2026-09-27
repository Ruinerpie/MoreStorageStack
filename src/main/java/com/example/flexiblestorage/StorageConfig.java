package com.example.flexiblestorage;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class StorageConfig {
    private StorageConfig() {}

    private static final int MIN = 1;
    private static final int MAX = 99; 
    private static volatile int maxStackSize = 64;

    private static final Logger LOG = LoggerFactory.getLogger("flexiblestorage");

    static {
        if (MIN > MAX) {
            throw new IllegalStateException("Invalid configuration bounds: MIN cannot be greater than MAX.");
        }
    }

    public static int getMaxStackSize() {
        return maxStackSize;
    }

    public static void setMaxStackSize(int value) {
        maxStackSize = clamp(value);
    }

    public static int clamp(int value) {
        return Math.max(MIN, Math.min(MAX, value));
    }

    public static void load() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve("flexiblestorage.properties");
        try {
            if (Files.notExists(file)) {
                Files.createDirectories(file.getParent());
                Files.writeString(file, "max_stack_size=64\n");
                setMaxStackSize(64);
                return;
            }
            Properties p = new Properties();
            try (var r = Files.newBufferedReader(file)) { 
                p.load(r); 
            }
            int raw = Integer.parseInt(p.getProperty("max_stack_size", "64"));
            setMaxStackSize(raw);
        } catch (Exception e) {
            LOG.error("Failed to load flexiblestorage config, using default 64", e);
            setMaxStackSize(64);
        }
    }
}
