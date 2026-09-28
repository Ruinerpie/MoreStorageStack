package com.ruinerpie.morestoragestack.preset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PresetDetector {
    private PresetDetector() {}
    private static final Logger LOG = LoggerFactory.getLogger("morestoragestack");

    public static StackPreset detect(int value) {
        StackPreset preset = StackPreset.fromValue(value);
        if (preset == StackPreset.BULK) {
            LOG.warn("═══════════════════════════════════════════════");
            LOG.warn(" BULK preset active — max_stack_size = {}", value);
            LOG.warn(" Values above {} exceed Minecraft's byte storage.", StackPreset.byteSafeMax());
            LOG.warn(" The mod caps real storage at {} to protect your save.", StackPreset.byteSafeMax());
            LOG.warn(" Both client and server must use the same value.");
            LOG.warn("═══════════════════════════════════════════════");
        } else {
            LOG.info("SAFE preset active — max_stack_size = {}", value);
        }
        return preset;
    }
}
