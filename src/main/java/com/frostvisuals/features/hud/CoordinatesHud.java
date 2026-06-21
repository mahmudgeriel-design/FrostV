package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

public class CoordinatesHud extends HudElement {
    public CoordinatesHud() { super("Coordinates", 0.01f, 0.10f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        String text = String.format("XYZ: %.1f / %.1f / %.1f",
                mc.player.getX(), mc.player.getY(), mc.player.getZ());
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
