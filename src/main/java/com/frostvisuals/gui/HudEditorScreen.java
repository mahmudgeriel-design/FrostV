package com.frostvisuals.gui;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.hud.HudElement;
import com.frostvisuals.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

/**
 * HUD editor — drag elements around the screen.
 * Renders each HudElement at its fractional position scaled to the current
 * GUI-scaled resolution, so what you see is exactly what you get in-game
 * at any GUI scale setting.
 */
public class HudEditorScreen extends Screen {
    private HudElement dragging;

    public HudEditorScreen() { super(new StringTextComponent("HUD Editor")); }

    @Override
    public void render(MatrixStack ms, int mx, int my, float pt) {
        // Dim background
        RenderUtils.drawRect(ms, 0, 0, width, height, 0x88000000);

        // Render all enabled HUD elements at their current positions
        if (FrostVisuals.featureManager != null) {
            for (Feature f : FrostVisuals.featureManager.getFeatures()) {
                if (!(f instanceof HudElement) || !f.isEnabled()) continue;
                HudElement el = (HudElement) f;

                // Force render so we get correct width/height
                el.render(ms, pt);

                // Draw selection box
                RenderUtils.drawOutline(ms, el.getRenderX() - 1, el.getRenderY() - 1,
                        el.getWidth() + 2, el.getHeight() + 2, 0xFF00AAFF);
            }
        }

        // Help text — bottom centre
        drawCenteredString(ms, font, "\u00a77Drag elements \u00a7f\u2022 \u00a77ESC to close", width / 2, height - 12, 0xFFFFFF);

        super.render(ms, mx, my, pt);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int btn) {
        if (btn == 0 && FrostVisuals.featureManager != null) {
            for (Feature f : FrostVisuals.featureManager.getFeatures()) {
                if (!(f instanceof HudElement) || !f.isEnabled()) continue;
                HudElement el = (HudElement) f;
                if (el.onMouseDown(mx, my)) { dragging = el; return true; }
            }
        }
        return super.mouseClicked(mx, my, btn);
    }

    @Override
    public boolean mouseDragged(double mx, double my, int btn, double dx, double dy) {
        if (dragging != null) { dragging.onMouseDrag(mx, my); return true; }
        return super.mouseDragged(mx, my, btn, dx, dy);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int btn) {
        if (dragging != null) { dragging.onMouseUp(); dragging = null; return true; }
        return super.mouseReleased(mx, my, btn);
    }

    @Override public boolean isPauseScreen() { return false; }
}
