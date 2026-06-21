package com.frostvisuals.mixin;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.visuals.Chams;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingRenderer.class)
public class MixinLivingRenderer<T extends LivingEntity> {
    @Inject(method = "render", at = @At("HEAD"))
    private void preRender(T entity, float yaw, float partialTick, MatrixStack ms, IRenderTypeBuffer buf, int light, CallbackInfo ci) {
        if (FrostVisuals.featureManager == null) return;
        Chams chams = (Chams) FrostVisuals.featureManager.getFeature("Chams");
        if (chams != null && chams.isEnabled()) chams.preRender(ms);
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void postRender(T entity, float yaw, float partialTick, MatrixStack ms, IRenderTypeBuffer buf, int light, CallbackInfo ci) {
        if (FrostVisuals.featureManager == null) return;
        Chams chams = (Chams) FrostVisuals.featureManager.getFeature("Chams");
        if (chams != null && chams.isEnabled()) chams.postRender(ms);
    }
}
