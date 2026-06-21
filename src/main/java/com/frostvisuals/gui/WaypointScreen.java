package com.frostvisuals.gui;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.utils.RenderUtils;
import com.frostvisuals.waypoints.Waypoint;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import java.awt.Color;
import java.util.List;

public class WaypointScreen extends Screen {
    public WaypointScreen() { super(new StringTextComponent("Waypoints")); }

    @Override
    public void render(MatrixStack ms, int mx, int my, float pt) {
        RenderUtils.drawRect(ms, width/2 - 130, 20, 260, height - 40, 0xDD0D0D14);
        RenderUtils.drawOutline(ms, width/2 - 130, 20, 260, height - 40, 0xFF00AAFF);
        drawCenteredString(ms, font, "\u00a7b\u2744 Waypoints", width/2, 28, 0xFFFFFF);

        if (FrostVisuals.waypointManager == null) return;
        List<Waypoint> wps = FrostVisuals.waypointManager.getWaypoints();
        int y = 45;
        for (Waypoint wp : wps) {
            String label = wp.getName() + " [" + wp.getPos().getX() + ", " + wp.getPos().getY() + ", " + wp.getPos().getZ() + "]";
            RenderUtils.drawRect(ms, width/2 - 125, y, 220, 14, 0xFF1A1A2E);
            drawString(ms, font, label, width/2 - 122, y + 3, wp.getColor() | 0xFF000000);
            y += 18;
        }

        // Add current pos button
        int bx = width/2 - 60, by = height - 35;
        RenderUtils.drawRect(ms, bx, by, 120, 16, 0xFF003366);
        RenderUtils.drawOutline(ms, bx, by, 120, 16, 0xFF0088FF);
        drawCenteredString(ms, font, "+ Add Waypoint Here", width/2, by + 4, 0xFFFFFFFF);

        super.render(ms, mx, my, pt);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int btn) {
        int bx = width/2 - 60, by = height - 35;
        if (mx >= bx && mx <= bx+120 && my >= by && my <= by+16) {
            if (minecraft != null && minecraft.player != null && FrostVisuals.waypointManager != null) {
                BlockPos pos = minecraft.player.blockPosition();
                FrostVisuals.waypointManager.add(new Waypoint("WP " + pos.getX(), pos, Color.CYAN.getRGB(), "overworld"));
            }
            return true;
        }
        return super.mouseClicked(mx, my, btn);
    }

    @Override public boolean isPauseScreen() { return false; }
}
