package com.ruinerpie.morestoragestack;

import net.fabricmc.api.ModInitializer;

public class MoreStorageStackMod implements ModInitializer {
    @Override
    public void onInitialize() {
        StorageConfig.load();
    }
}
