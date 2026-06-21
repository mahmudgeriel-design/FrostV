package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;

public class ComboHud extends HudElement {
    private int combo;
    private long lastHit;

    public ComboHud() { super("Combo", 0.50f, 0.40f); }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (e.getSource().getEntity() == mc.player) {
            long now = System.currentTimeMillis();
            if (now - lastHit < 3000) combo++;
            else combo = 1;
            lastHit = now;
        }
    }

    @Override
    public void render(MatrixStack ms, float pt) {
        if (combo <= 1) { width = 0; height = 0; return; }
        if (System.currentTimeMillis() - lastHit > 3000) { combo = 0; width = 0; height = 0; return; }
        String text = combo + "x Combo!";
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
