package com.frostvisuals.mixin;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.utilities.ToggleSprint;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (FrostVisuals.featureManager == null) return;
        ToggleSprint ts = (ToggleSprint) FrostVisuals.featureManager.getFeature("Toggle Sprint");
        if (ts != null && ts.isEnabled()) ts.onPlayerTick((PlayerEntity)(Object)this);
    }
}
