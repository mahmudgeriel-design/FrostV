package com.frostvisuals.features.hud;

import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.BoolSetting;
import com.frostvisuals.features.settings.ColorSetting;
import com.frostvisuals.utils.ColorUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.Color;

/**
 * Base class for all draggable HUD elements.
 *
 * Positions are stored as FRACTIONS (0.0–1.0) of the GUI-scaled screen,
 * so elements stay correctly placed at any GUI scale setting (1x–4x, Auto).
 *
 * Actual pixel coordinates = posX * guiScaledWidth, posY * guiScaledHeight.
 * This way, if the user changes scale from 2x to 3x, everything stays put.
 */
public abstract class HudElement extends Feature {

    /** Position as fraction of scaled screen (0.0 – 1.0) */
    protected float posX;
    protected float posY;

    /** Pixel width/height of this element — set during render */
    protected int width;
    protected int height;

    protected boolean dragging;
    protected double dragOffsetX, dragOffsetY;

    public final BoolSetting shadow = new BoolSetting("Shadow", true);
    public final BoolSetting background = new BoolSetting("Background", false);
    public final ColorSetting color = new ColorSetting("Color", new Color(255, 255, 255));

    public HudElement(String name, float defaultXFraction, float defaultYFraction) {
        super(name, Category.HUD);
        this.posX = defaultXFraction;
        this.posY = defaultYFraction;
        settings.add(shadow);
        settings.add(background);
        settings.add(color);
    }

    // ── Scale-aware coordinate helpers ───────────────────────────────────────

    /** Scaled width of the Minecraft window in GUI units */
    protected int scaledW() {
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    /** Scaled height of the Minecraft window in GUI units */
    protected int scaledH() {
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    /** Actual pixel X from fractional position */
    public int getRenderX() {
        return Math.round(posX * scaledW());
    }

    /** Actual pixel Y from fractional position */
    public int getRenderY() {
        return Math.round(posY * scaledH());
    }

    /** Update fractional position from pixel coords (used by drag) */
    public void setRenderPos(double px, double py) {
        int sw = scaledW();
        int sh = scaledH();
        posX = (float) Math.max(0, Math.min((sw - width)  / (double) sw,  px / sw));
        posY = (float) Math.max(0, Math.min((sh - height) / (double) sh, py / sh));
    }

    // ── Dragging ─────────────────────────────────────────────────────────────

    public boolean onMouseDown(double mx, double my) {
        int rx = getRenderX(), ry = getRenderY();
        if (mx >= rx && mx <= rx + width && my >= ry && my <= ry + height) {
            dragging = true;
            dragOffsetX = mx - rx;
            dragOffsetY = my - ry;
            return true;
        }
        return false;
    }

    public void onMouseDrag(double mx, double my) {
        if (dragging) setRenderPos(mx - dragOffsetX, my - dragOffsetY);
    }

    public void onMouseUp() { dragging = false; }

    // ── Rendering ────────────────────────────────────────────────────────────

    /** Called by HudRenderer every frame. Subclasses implement their content here. */
    public abstract void render(MatrixStack ms, float partialTicks);

    protected FontRenderer font() {
        return Minecraft.getInstance().font;
    }

    protected int textColor() {
        return color.isRainbow()
                ? ColorUtils.rainbow(System.currentTimeMillis(), 1000)
                : color.getARGB();
    }

    protected void drawText(MatrixStack ms, String text, int x, int y) {
        if (shadow.isEnabled()) {
            font().drawShadow(ms, text, x, y, textColor());
        } else {
            font().draw(ms, text, x, y, textColor());
        }
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────

    public float getPosX() { return posX; }
    public float getPosY() { return posY; }
    public void setPosX(float v) { posX = v; }
    public void setPosY(float v) { posY = v; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
