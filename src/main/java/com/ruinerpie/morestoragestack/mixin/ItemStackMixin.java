package com.ruinerpie.morestoragestack.mixin;

import com.ruinerpie.morestoragestack.StorageConfig;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getMaxCount", at = @At("RETURN"), cancellable = true)
    private void morestoragestack$scale(CallbackInfoReturnable<Integer> cir) {
        int original = cir.getReturnValue();
        if (original <= 1) {
            cir.setReturnValue(1);
        } else if (original < StorageConfig.getMaxStackSize()) {
            cir.setReturnValue(StorageConfig.getMaxStackSize());
        }
    }
}
