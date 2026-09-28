package com.ruinerpie.morestoragestack.mixin;

import com.ruinerpie.morestoragestack.StorageConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    private static final int BYTE_SAFE_MAX = 127;

    @ModifyReturnValue(method = "getMaxCount", at = @At("RETURN"))
    private int morestoragestack$scale(int original) {
        if (original <= 1) return 1;

        int userMax = StorageConfig.getMaxStackSize();
        int effective = Math.min(userMax, BYTE_SAFE_MAX);
        return Math.max(original, effective);
    }
}
