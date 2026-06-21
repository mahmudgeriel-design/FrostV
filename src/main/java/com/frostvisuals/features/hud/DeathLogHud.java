package com.frostvisuals.features.hud;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import java.util.*;

public class DeathLogHud extends HudElement {
    private final List<String> log = new ArrayList<>();
    public DeathLogHud() { super("Death Log", 0.70f, 0.20f); }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        if (!(e.getEntity() instanceof PlayerEntity)) return;
        String victim = e.getEntity().getName().getString();
        String killer = e.getSource().getEntity() != null ? e.getSource().getEntity().getName().getString() : "?";
        log.add(0, killer + " \u00bb " + victim);
        if (log.size() > 8) log.remove(log.size() - 1);
    }

    @Override
    public void render(MatrixStack ms, float pt) {
        if (log.isEmpty()) { width = 0; height = 0; return; }
        int x = getRenderX(), y = getRenderY();
        width = 0; height = 0;
        for (String entry : log) {
            drawText(ms, entry, x, y + height);
            width = Math.max(width, font().width(entry));
            height += font().lineHeight + 1;
        }
    }
}
