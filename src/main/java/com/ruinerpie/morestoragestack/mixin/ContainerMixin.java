package com.ruinerpie.morestoragestack.mixin;

import com.ruinerpie.morestoragestack.StorageConfig;
import com.ruinerpie.morestoragestack.preset.StackPreset;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Container.class)
public interface ContainerMixin {

    @Inject(method = "getMaxStackSize()I", at = @At("RETURN"), cancellable = true)
    private void morestoragestack$overrideMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        int original = cir.getReturnValue();
        int userMax = StorageConfig.getMaxStackSize();

        if (original < userMax) {
            cir.setReturnValue(userMax);
        }
    }
}
