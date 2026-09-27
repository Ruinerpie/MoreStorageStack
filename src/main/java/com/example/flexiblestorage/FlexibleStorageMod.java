package com.example.flexiblestorage;

import net.fabricmc.api.ModInitializer;

public class FlexibleStorageMod implements ModInitializer {
    @Override
    public void onInitialize() {
        StorageConfig.load();
    }
}
