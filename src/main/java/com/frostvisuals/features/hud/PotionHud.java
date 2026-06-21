package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class PotionHud extends HudElement {
    public PotionHud() { super("Potion HUD", 0.85f, 0.05f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        int x = getRenderX(), y = getRenderY();
        width = 0; height = 0;
        for (EffectInstance eff : mc.player.getActiveEffects()) {
            Effect effect = eff.getEffect();
            int secs = eff.getDuration() / 20;
            String dur = secs > 3600 ? "\u221e" : String.format("%d:%02d", secs / 60, secs % 60);
            String line = effect.getDisplayName().getString() + " " + (eff.getAmplifier() + 1) + " (" + dur + ")";
            drawText(ms, line, x, y);
            y += font().lineHeight + 1;
            width = Math.max(width, font().width(line));
            height += font().lineHeight + 1;
        }
    }
}
