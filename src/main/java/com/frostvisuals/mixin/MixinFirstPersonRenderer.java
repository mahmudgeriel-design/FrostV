package com.frostvisuals.mixin;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.utilities.OldAnimations;
import net.minecraft.client.renderer.FirstPersonRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FirstPersonRenderer.class)
public class MixinFirstPersonRenderer {
    @Inject(method = "renderArmWithItem", at = @At("HEAD"))
    private void onRenderArm(CallbackInfo ci) {
    }
}
