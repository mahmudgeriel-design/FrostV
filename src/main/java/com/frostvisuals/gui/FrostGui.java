package com.frostvisuals.gui;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import java.util.List;

public class FrostGui extends Screen {
    private Category selectedCategory = Category.HUD;

    private static final int SIDEBAR_W = 90;
    private static final int PANEL_PAD = 8;

    public FrostGui() { super(new StringTextComponent("FrostVisuals")); }

    @Override
    public void render(MatrixStack ms, int mx, int my, float pt) {
        // Background
        RenderUtils.drawRect(ms, 0, 0, width, height, 0xCC111118);

        // Sidebar
        RenderUtils.drawRect(ms, 0, 0, SIDEBAR_W, height, 0xDD0D0D14);

        // Title
        drawCenteredString(ms, font, "\u00a7b\u2744 FrostVisuals", SIDEBAR_W / 2, 8, 0xFFFFFF);

        // Category tabs
        Category[] cats = Category.values();
        for (int i = 0; i < cats.length; i++) {
            Category c = cats[i];
            int ty = 26 + i * 18;
            boolean sel = c == selectedCategory;
            RenderUtils.drawRect(ms, 4, ty, SIDEBAR_W - 8, 16, sel ? 0xFF1A1A2E : 0xFF0A0A12);
            if (sel) RenderUtils.drawRect(ms, 4, ty, 2, 16, 0xFF00AAFF);
            drawString(ms, font, c.getDisplayName(), 12, ty + 4, sel ? 0xFF00AAFF : 0xFFAAAAAA);
        }

        // Feature list
        if (FrostVisuals.featureManager == null) return;
        List<Feature> list = FrostVisuals.featureManager.getByCategory(selectedCategory);
        int fx = SIDEBAR_W + PANEL_PAD;
        int fy = 16;
        for (Feature f : list) {
            boolean en = f.isEnabled();
            int bg = en ? 0xFF1A2A1A : 0xFF1A1A1A;
            int tw = width - SIDEBAR_W - PANEL_PAD * 2;
            RenderUtils.drawRect(ms, fx, fy, tw, 18, bg);
            RenderUtils.drawOutline(ms, fx, fy, tw, 18, en ? 0xFF00CC44 : 0xFF333333);
            int dot = en ? 0xFF00FF66 : 0xFF666666;
            RenderUtils.drawRect(ms, fx + 4, fy + 6, 6, 6, dot);
            drawString(ms, font, f.getName(), fx + 14, fy + 5, en ? 0xFFFFFFFF : 0xFFAAAAAA);
            fy += 22;
            if (fy > height - 20) break;
        }

        super.render(ms, mx, my, pt);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int btn) {
        // Category click
        Category[] cats = Category.values();
        for (int i = 0; i < cats.length; i++) {
            int ty = 26 + i * 18;
            if (mx >= 4 && mx <= SIDEBAR_W - 4 && my >= ty && my <= ty + 16) {
                selectedCategory = cats[i];
                return true;
            }
        }
        // Feature toggle
        if (FrostVisuals.featureManager == null) return super.mouseClicked(mx, my, btn);
        List<Feature> list = FrostVisuals.featureManager.getByCategory(selectedCategory);
        int fx = SIDEBAR_W + PANEL_PAD;
        int fy = 16;
        for (Feature f : list) {
            int tw = width - SIDEBAR_W - PANEL_PAD * 2;
            if (mx >= fx && mx <= fx + tw && my >= fy && my <= fy + 18) {
                f.toggle();
                return true;
            }
            fy += 22;
        }
        return super.mouseClicked(mx, my, btn);
    }

    @Override public boolean isPauseScreen() { return false; }
}
