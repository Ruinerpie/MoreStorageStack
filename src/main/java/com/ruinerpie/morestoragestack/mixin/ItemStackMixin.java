package com.ruinerpie.morestoragestack.mixin;

import com.ruinerpie.morestoragestack.StorageConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    private static final int BYTE_SAFE_MAX = 127;

    public int getMaxStackSize() {
        int original = ((ItemStack) (Object) this).getOrDefault(DataComponents.MAX_STACK_SIZE, 1);
        if (original <= 1) return 1;

        int userMax = StorageConfig.getMaxStackSize();
        int effective = Math.min(userMax, BYTE_SAFE_MAX);
        return Math.max(original, effective);
    }
}
