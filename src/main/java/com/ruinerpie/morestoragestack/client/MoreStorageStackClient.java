package com.ruinerpie.morestoragestack.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class MoreStorageStackClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyMapping openKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.morestoragestack.open",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openKey.consumeClick()) {
                if (client.screen == null) {
                    client.setScreen(new MoreStorageStackScreen(null));
                }
            }
        });
    }
}
