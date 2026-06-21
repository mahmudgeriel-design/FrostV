package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

public class BpsHud extends HudElement {
    private double lastX, lastZ;
    private long lastTime;
    private double bps;

    public BpsHud() { super("BPS", 0.01f, 0.16f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        long now = System.currentTimeMillis();
        double cx = mc.player.getX(), cz = mc.player.getZ();
        if (lastTime != 0) {
            double dt = (now - lastTime) / 1000.0;
            if (dt > 0) bps = Math.sqrt(Math.pow(cx - lastX, 2) + Math.pow(cz - lastZ, 2)) / dt;
        }
        lastX = cx; lastZ = cz; lastTime = now;
        String text = String.format("BPS: %.2f", bps);
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
