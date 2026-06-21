package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

public class ChunkHud extends HudElement {
    public ChunkHud() { super("Chunk", 0.01f, 0.25f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        BlockPos pos = mc.player.blockPosition();
        int cx = pos.getX() >> 4, cz = pos.getZ() >> 4;
        int lx = pos.getX() & 15, lz = pos.getZ() & 15;
        String text = "Chunk: " + cx + ", " + cz + " [" + lx + ", " + lz + "]";
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
