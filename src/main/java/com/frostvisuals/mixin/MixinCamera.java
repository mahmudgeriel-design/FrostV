package com.frostvisuals.mixin;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.visuals.Freelook;
import net.minecraft.client.renderer.ActiveRenderInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ActiveRenderInfo.class)
public class MixinCamera {
    @Inject(method = "setup", at = @At("RETURN"))
    private void onSetup(CallbackInfo ci) {
    }
}
