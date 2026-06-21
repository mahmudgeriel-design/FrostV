package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;

public class ReachHud extends HudElement {
    private double lastReach;
    public ReachHud() { super("Reach", 0.01f, 0.37f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) { width = 0; height = 0; return; }
        RayTraceResult hit = mc.hitResult;
        if (hit instanceof EntityRayTraceResult) {
            Entity e = ((EntityRayTraceResult) hit).getEntity();
            lastReach = mc.player.distanceTo(e);
        }
        String text = String.format("Reach: %.2f", lastReach);
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
