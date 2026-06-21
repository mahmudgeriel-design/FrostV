package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;

public class PingHud extends HudElement {
    public PingHud() { super("Ping", 0.01f, 0.07f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        int ping = 0;
        if (mc.player != null && mc.getConnection() != null) {
            NetworkPlayerInfo info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
            if (info != null) ping = info.getLatency();
        }
        String text = "Ping: " + ping + "ms";
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
