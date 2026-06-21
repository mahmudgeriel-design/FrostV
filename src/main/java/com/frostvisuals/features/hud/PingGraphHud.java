package com.frostvisuals.features.hud;
import com.frostvisuals.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import java.util.*;

public class PingGraphHud extends HudElement {
    private final Deque<Integer> samples = new ArrayDeque<>();
    private long lastSample;

    public PingGraphHud() { super("Ping Graph", 0.01f, 0.60f); width = 80; height = 30; }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.getConnection() == null) return;

        long now = System.currentTimeMillis();
        if (now - lastSample > 500) {
            NetworkPlayerInfo info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
            if (info != null) { samples.addLast(info.getLatency()); lastSample = now; }
            while (samples.size() > 40) samples.pollFirst();
        }

        int x = getRenderX(), y = getRenderY();
        RenderUtils.drawRect(ms, x, y, width, height, 0xAA000000);
        if (samples.size() < 2) return;
        int maxPing = samples.stream().mapToInt(v -> v).max().orElse(1);
        int i = 0;
        Integer prev = null;
        for (int val : samples) {
            int bx = x + i * width / 40;
            int by = y + height - (val * (height - 2) / Math.max(maxPing, 1)) - 1;
            if (prev != null) {
                int prevY = y + height - (prev * (height - 2) / Math.max(maxPing, 1)) - 1;
                RenderUtils.drawRect(ms, bx - 1, Math.min(by, prevY), 1, Math.abs(by - prevY) + 1, 0xFF00AAFF);
            }
            prev = val; i++;
        }
    }
}
