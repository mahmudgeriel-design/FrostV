package com.frostvisuals.features.hud;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import java.util.List;

public class NearPlayersHud extends HudElement {
    public NearPlayersHud() { super("Near Players", 0.85f, 0.50f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;
        int x = getRenderX(), y = getRenderY();
        width = 0; height = 0;
        List<? extends PlayerEntity> players = mc.level.players();
        int count = 0;
        for (PlayerEntity p : players) {
            if (p == mc.player) continue;
            double dist = mc.player.distanceTo(p);
            if (dist > 64) continue;
            String line = p.getName().getString() + " - " + (int)dist + "m";
            drawText(ms, line, x, y + count * (font().lineHeight + 1));
            width = Math.max(width, font().width(line));
            height += font().lineHeight + 1;
            count++;
        }
    }
}
