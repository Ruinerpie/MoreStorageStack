package com.ruinerpie.morestoragestack;

import com.ruinerpie.morestoragestack.preset.PresetDetector;
import com.ruinerpie.morestoragestack.preset.StackPreset;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class StorageConfig {
    private StorageConfig() {}

    private static final int MIN = 64;
    private static final int MAX = 4096; 
    private static volatile int maxStackSize = 64;
    private static volatile StackPreset activePreset = StackPreset.SAFE;

    private static final Logger LOG = LoggerFactory.getLogger("morestoragestack");

    static {
        if (MIN > MAX) {
            throw new IllegalStateException("Invalid configuration bounds: MIN cannot be greater than MAX.");
        }
    }

    public static int getMaxStackSize() {
        return maxStackSize;
    }

    public static StackPreset getActivePreset() {
        return activePreset;
    }

    public static void setMaxStackSize(int value) {
        maxStackSize = clamp(value);
        activePreset = PresetDetector.detect(maxStackSize);
    }

    public static int clamp(int value) {
        return Math.max(MIN, Math.min(MAX, value));
    }

    public static void load() {
        Path file = FabricLoader.getInstance().getConfigDir().resolve("morestoragestack.properties");
        try {
            if (Files.notExists(file)) {
                Files.createDirectories(file.getParent());
                Files.writeString(file,
                        "# MoreStorageStack configuration\n" +
                        "#\n" +
                        "# Range: 64 to 4096\n" +
                        "#   SAFE preset: 64 - 127   (stable, recommended)\n" +
                        "#   BULK preset: 128 - 4096 (advanced, read README first)\n" +
                        "#\n" +
                        "max_stack_size=64\n");
                setMaxStackSize(64);
                return;
            }
            Properties p = new Properties();
            try (var r = Files.newBufferedReader(file)) { 
                p.load(r); 
            }
            String rawStr = p.getProperty("max_stack_size", "64");
            int raw = Integer.parseInt(rawStr != null ? rawStr.trim() : "64");
            setMaxStackSize(raw);
        } catch (Exception e) {
            LOG.error("Failed to load morestoragestack config, using default 64", e);
            setMaxStackSize(64);
        }
    }

    public static void save() {
        Path file = FabricLoader.getInstance()
                .getConfigDir()
                .resolve("morestoragestack.properties");
        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file,
                    "# MoreStorageStack configuration\n" +
                    "#\n" +
                    "# Range: 64 to 4096\n" +
                    "#   SAFE preset: 64 - 127   (stable, recommended)\n" +
                    "#   BULK preset: 128 - 4096 (advanced, read README first)\n" +
                    "#\n" +
                    "max_stack_size=" + maxStackSize + "\n");
            LOG.info("Saved max_stack_size = {}", maxStackSize);
        } catch (Exception e) {
            LOG.error("Failed to save config", e);
        }
    }
}
