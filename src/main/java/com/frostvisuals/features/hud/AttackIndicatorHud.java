package com.frostvisuals.features.hud;
import com.frostvisuals.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;

public class AttackIndicatorHud extends HudElement {
    public AttackIndicatorHud() { super("Attack Indicator", 0.50f, 0.55f); width = 50; height = 5; }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        float cooldown = mc.player.getAttackStrengthScale(pt);
        int x = getRenderX(), y = getRenderY();
        RenderUtils.drawRect(ms, x, y, width, height, 0xFF333333);
        int filled = (int)(cooldown * width);
        int color = cooldown >= 1f ? 0xFF55FF55 : 0xFFFF5500;
        if (filled > 0) RenderUtils.drawRect(ms, x, y, filled, height, color);
        RenderUtils.drawOutline(ms, x, y, width, height, 0xFFAAAAAA);
    }
}
