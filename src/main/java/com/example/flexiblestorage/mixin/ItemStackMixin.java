package com.example.flexiblestorage.mixin;

import com.example.flexiblestorage.StorageConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @ModifyReturnValue(method = "getMaxCount", at = @At("RETURN"))
    private int flexiblestorage$scale(int original) {
        if (original <= 1) {
            return 1;
        }
        return Math.max(original, StorageConfig.getMaxStackSize());
    }
}
