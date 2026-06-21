package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class ArmorHud extends HudElement {
    public ArmorHud() { super("Armor HUD", 0.01f, 0.31f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        int x = getRenderX(), y = getRenderY();
        width = 0; height = font().lineHeight;
        for (ItemStack stack : mc.player.getArmorSlots()) {
            if (stack.isEmpty()) continue;
            String name = stack.getDisplayName().getString();
            int dur = stack.getMaxDamage() - stack.getDamageValue();
            String line = name + " " + dur + "/" + stack.getMaxDamage();
            drawText(ms, line, x, y);
            y += font().lineHeight + 1;
            width = Math.max(width, font().width(line));
            height += font().lineHeight + 1;
        }
    }
}
