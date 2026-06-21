package com.frostvisuals.mixin;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.utilities.Zoom;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void onGetFov(float partialTicks, boolean useFOVSetting, CallbackInfoReturnable<Double> cir) {
        if (FrostVisuals.featureManager == null) return;
        Zoom zoom = (Zoom) FrostVisuals.featureManager.getFeature("Zoom");
        if (zoom != null && zoom.isEnabled() && zoom.isZooming()) {
            cir.setReturnValue(zoom.getZoomFov(cir.getReturnValue()));
        }
    }
}
