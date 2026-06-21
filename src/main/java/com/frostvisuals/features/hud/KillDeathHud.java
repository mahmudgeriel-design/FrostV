package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class KillDeathHud extends HudElement {
    private int kills, deaths;
    public KillDeathHud() { super("K/D Ratio", 0.01f, 0.34f); }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (e.getEntity() == mc.player) { deaths++; return; }
        if (e.getSource().getEntity() == mc.player && e.getEntity() instanceof PlayerEntity) kills++;
    }

    @Override
    public void render(MatrixStack ms, float pt) {
        float kd = deaths == 0 ? kills : (float) kills / deaths;
        String text = String.format("K/D: %d/%d (%.2f)", kills, deaths, kd);
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
