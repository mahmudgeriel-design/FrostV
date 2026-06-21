package com.frostvisuals.features.hud;
import com.frostvisuals.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.*;

public class CpsGraphHud extends HudElement {
    private final Deque<Long> clicks = new ArrayDeque<>();
    private final int[] history = new int[40];
    private int idx;
    private long lastSample;

    public CpsGraphHud() { super("CPS Graph", 0.01f, 0.65f); width = 80; height = 30; }

    @SubscribeEvent
    public void onClick(InputEvent.RawMouseEvent e) {
        if (e.getButton() == 0) clicks.addLast(System.currentTimeMillis());
    }

    @Override
    public void render(MatrixStack ms, float pt) {
        long now = System.currentTimeMillis();
        clicks.removeIf(t -> t < now - 1000);
        if (now - lastSample > 100) {
            history[idx++ % 40] = clicks.size();
            lastSample = now;
        }
        int x = getRenderX(), y = getRenderY();
        RenderUtils.drawRect(ms, x, y, width, height, 0xAA000000);
        int maxCps = 1;
        for (int v : history) maxCps = Math.max(maxCps, v);
        for (int i = 0; i < 40; i++) {
            int bh = history[(idx + i) % 40] * (height - 2) / maxCps;
            RenderUtils.drawRect(ms, x + i * 2, y + height - bh - 1, 1, bh, 0xFFFF5500);
        }
    }
}
