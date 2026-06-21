package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class BiomeHud extends HudElement {
    public BiomeHud() { super("Biome", 0.01f, 0.22f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        BlockPos pos = mc.player.blockPosition();
        Biome biome = mc.level.getBiome(pos);
        String name = biome.getRegistryName() != null ? biome.getRegistryName().getPath() : "unknown";
        name = name.replace("_", " ");
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        String text = "Biome: " + name;
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
