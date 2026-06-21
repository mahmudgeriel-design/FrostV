package com.frostvisuals.mixin;

import com.frostvisuals.config.FrostConfig;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "stop", at = @At("HEAD"))
    private void onStop(CallbackInfo ci) {
        FrostConfig.save();
    }
}
