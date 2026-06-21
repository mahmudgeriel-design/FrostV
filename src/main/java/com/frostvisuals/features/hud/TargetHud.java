package com.frostvisuals.features.hud;

import com.frostvisuals.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class TargetHud extends HudElement {
    public TargetHud() { super("Target HUD", 0.40f, 0.70f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        LivingEntity target = findTarget(mc);
        if (target == null) { width = 0; height = 0; return; }

        int x = getRenderX(), y = getRenderY();
        width  = 120;
        height = 28;
        RenderUtils.drawRect(ms, x, y, width, height, 0xBB000000);
        RenderUtils.drawOutline(ms, x, y, width, height, 0xFFFFFFFF);

        String name = target.getName().getString();
        float hp = target.getHealth();
        float maxHp = target.getMaxHealth();
        int hpColor = hp > maxHp * 0.5f ? 0xFF55FF55 : hp > maxHp * 0.25f ? 0xFFFFAA00 : 0xFFFF5555;

        drawText(ms, name, x + 3, y + 3);
        drawText(ms, String.format("\u2665 %.1f/%.1f", hp, maxHp), x + 3, y + 14);

        int barW = (int)((hp / maxHp) * (width - 6));
        RenderUtils.drawRect(ms, x + 3, y + 23, width - 6, 3, 0xFF333333);
        RenderUtils.drawRect(ms, x + 3, y + 23, barW, 3, hpColor);
    }

    private LivingEntity findTarget(Minecraft mc) {
        LivingEntity closest = null;
        double minDist = 8.0;
        if (mc.level == null) return null;
        for (PlayerEntity p : mc.level.players()) {
            if (p == mc.player) continue;
            double d = mc.player.distanceTo(p);
            if (d < minDist) { minDist = d; closest = p; }
        }
        return closest;
    }
}
