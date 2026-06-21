package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Direction;

public class DirectionHud extends HudElement {
    public DirectionHud() { super("Direction", 0.01f, 0.19f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        Direction dir = mc.player.getDirection();
        float yaw = mc.player.yRot % 360;
        if (yaw < 0) yaw += 360;
        String text = "Dir: " + dir.getName().toUpperCase() + " (" + (int)yaw + "\u00b0)";
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
