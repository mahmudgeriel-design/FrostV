package com.frostvisuals.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderUtils {

    public static void drawRect(MatrixStack ms, int x, int y, int w, int h, int color) {
        float a = ((color >> 24) & 0xFF) / 255f;
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;
        Matrix4f mat = ms.last().pose();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        BufferBuilder buf = Tessellator.getInstance().getBuilder();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        buf.vertex(mat, x,     y + h, 0).color(r, g, b, a).endVertex();
        buf.vertex(mat, x + w, y + h, 0).color(r, g, b, a).endVertex();
        buf.vertex(mat, x + w, y,     0).color(r, g, b, a).endVertex();
        buf.vertex(mat, x,     y,     0).color(r, g, b, a).endVertex();
        buf.end();
        WorldVertexBufferUploader.end(buf);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawGradientRect(MatrixStack ms, int x, int y, int w, int h, int colorTop, int colorBottom) {
        float aT = ((colorTop >> 24) & 0xFF) / 255f, rT = ((colorTop >> 16) & 0xFF) / 255f;
        float gT = ((colorTop >> 8) & 0xFF) / 255f,  bT = (colorTop & 0xFF) / 255f;
        float aB = ((colorBottom >> 24) & 0xFF) / 255f, rB = ((colorBottom >> 16) & 0xFF) / 255f;
        float gB = ((colorBottom >> 8) & 0xFF) / 255f,  bB = (colorBottom & 0xFF) / 255f;
        Matrix4f mat = ms.last().pose();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        BufferBuilder buf = Tessellator.getInstance().getBuilder();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        buf.vertex(mat, x,     y + h, 0).color(rB, gB, bB, aB).endVertex();
        buf.vertex(mat, x + w, y + h, 0).color(rB, gB, bB, aB).endVertex();
        buf.vertex(mat, x + w, y,     0).color(rT, gT, bT, aT).endVertex();
        buf.vertex(mat, x,     y,     0).color(rT, gT, bT, aT).endVertex();
        buf.end();
        WorldVertexBufferUploader.end(buf);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public static void drawOutline(MatrixStack ms, int x, int y, int w, int h, int color) {
        drawRect(ms, x,         y,         w, 1, color);
        drawRect(ms, x,         y + h - 1, w, 1, color);
        drawRect(ms, x,         y,         1, h, color);
        drawRect(ms, x + w - 1, y,         1, h, color);
    }

    /** Convert raw (un-scaled) mouse coordinates to GUI-scaled coordinates */
    public static double scaleMouseX(double rawX) {
        return rawX / Minecraft.getInstance().getWindow().getGuiScale();
    }
    public static double scaleMouseY(double rawY) {
        return rawY / Minecraft.getInstance().getWindow().getGuiScale();
    }
}
