package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

public class FpsHud extends HudElement {
    public FpsHud() { super("FPS", 0.01f, 0.01f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        int fps = Minecraft.getInstance().getFps();
        String text = "FPS: " + fps;
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
