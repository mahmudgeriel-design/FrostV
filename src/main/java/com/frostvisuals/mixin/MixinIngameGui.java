package com.frostvisuals.mixin;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.visuals.NoFire;
import net.minecraft.client.gui.IngameGui;
import com.mojang.blaze3d.matrix.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IngameGui.class)
public class MixinIngameGui {
    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderPortal(MatrixStack ms, float scale, CallbackInfo ci) {
        if (FrostVisuals.featureManager == null) return;
        NoFire nf = (NoFire) FrostVisuals.featureManager.getFeature("No Fire");
        if (nf != null && nf.isEnabled()) ci.cancel();
    }
}
