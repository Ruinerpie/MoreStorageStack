package com.ruinerpie.morestoragestack;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreStorageStackMod implements ModInitializer {
    public static final String MOD_ID = "morestoragestack";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing MoreStorageStack...");
        StorageConfig.load();
        LOGGER.info("Ready. Preset = {}, max_stack_size = {}",
                StorageConfig.getActivePreset().id(),
                StorageConfig.getMaxStackSize());
    }
}
