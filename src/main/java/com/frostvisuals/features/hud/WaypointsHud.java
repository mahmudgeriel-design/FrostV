package com.frostvisuals.features.hud;
import com.frostvisuals.FrostVisuals;
import com.frostvisuals.waypoints.Waypoint;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

public class WaypointsHud extends HudElement {
    public WaypointsHud() { super("Waypoints HUD", 0.01f, 0.55f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || FrostVisuals.waypointManager == null) return;
        int x = getRenderX(), y = getRenderY();
        width = 0; height = 0;
        for (Waypoint wp : FrostVisuals.waypointManager.getWaypoints()) {
            double dist = wp.distanceTo(mc.player.getX(), mc.player.getY(), mc.player.getZ());
            String line = wp.getName() + " - " + (int)dist + "m";
            drawText(ms, line, x, y + height);
            width = Math.max(width, font().width(line));
            height += font().lineHeight + 1;
        }
    }
}
