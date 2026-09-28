package com.ruinerpie.morestoragestack.mixin;

import com.ruinerpie.morestoragestack.StorageConfig;
import com.ruinerpie.morestoragestack.preset.StackPreset;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @ModifyArg(
        method = "lambda$static$1",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ExtraCodecs;intRange(II)Lcom/mojang/serialization/Codec;"),
        index = 1,
        require = 0
    )
    private static int morestoragestack$expandCodecMax(int originalMax) {
        return Math.max(originalMax, StackPreset.BULK.max());
    }

    public int getMaxStackSize() {
        int original = ((ItemStack) (Object) this).getOrDefault(DataComponents.MAX_STACK_SIZE, 1);
        if (original <= 1) return 1;

        int userMax = StorageConfig.getMaxStackSize();
        return Math.max(original, userMax);
    }
}
