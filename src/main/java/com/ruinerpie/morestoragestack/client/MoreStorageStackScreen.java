package com.ruinerpie.morestoragestack.client;

import com.ruinerpie.morestoragestack.StorageConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class MoreStorageStackScreen extends Screen {

    private static final int[] PRESETS = {64, 127, 128, 256, 512, 1024, 2048, 4096};
    private static final int DEFAULT_VALUE = 64;

    private final Screen parent;

    public MoreStorageStackScreen(Screen parent) {
        super(Component.literal("MoreStorageStack"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int cy = this.height / 2;

        int btnW = 80;
        int btnH = 20;
        int gap = 6;
        int totalW = 4 * btnW + 3 * gap;
        int startX = cx - totalW / 2;
        int startY = cy - 20;

        for (int i = 0; i < PRESETS.length; i++) {
            int value = PRESETS[i];
            int x = startX + (i % 4) * (btnW + gap);
            int y = startY + (i / 4) * (btnH + gap);

            boolean selected = StorageConfig.getMaxStackSize() == value;
            String label = (selected ? "▶ " : "") + value;

            this.addRenderableWidget(
                Button.builder(Component.literal(label), b -> {
                    StorageConfig.setMaxStackSize(value);
                    StorageConfig.save();
                    this.rebuildWidgets();
                }).bounds(x, y, btnW, btnH).build()
            );
        }

        this.addRenderableWidget(
            Button.builder(Component.literal("Reset to Default (64)"), b -> {
                StorageConfig.setMaxStackSize(DEFAULT_VALUE);
                StorageConfig.save();
                this.rebuildWidgets();
            }).bounds(cx - 100, cy + 56, 200, 20).build()
        );

        this.addRenderableWidget(
            Button.builder(Component.literal("Done"), b -> this.onClose())
                .bounds(cx - 100, cy + 80, 200, 20).build()
        );
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor extractor, int mouseX, int mouseY, float delta) {
        super.extractRenderState(extractor, mouseX, mouseY, delta);

        int cx = this.width / 2;
        int cy = this.height / 2;

        extractor.centeredText(this.font,
                Component.literal("MoreStorageStack").withStyle(ChatFormatting.BOLD),
                cx, cy - 90, 0xFFFFFF);

        extractor.centeredText(this.font,
                Component.literal("Max Stack Size: ")
                        .append(Component.literal(String.valueOf(StorageConfig.getMaxStackSize()))
                                .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD)),
                cx, cy - 70, 0xFFFFFF);

        boolean safe = StorageConfig.getActivePreset().id().equals("safe");
        Component preset = Component.literal("Preset: ").append(
                Component.literal(safe ? "SAFE" : "BULK")
                        .withStyle(safe ? ChatFormatting.GREEN : ChatFormatting.RED));
        extractor.centeredText(this.font, preset, cx, cy - 56, 0xFFFFFF);

        String hint = safe
                ? "SAFE: Recommended for stable survival & servers."
                : "BULK: High-capacity stack size (128 - 4096).";
        extractor.centeredText(this.font,
                Component.literal(hint).withStyle(ChatFormatting.GRAY),
                cx, cy + 34, 0xAAAAAA);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.parent);
    }
}
