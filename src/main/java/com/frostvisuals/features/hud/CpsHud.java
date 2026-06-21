package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import java.util.ArrayDeque;
import java.util.Deque;

public class CpsHud extends HudElement {
    private final Deque<Long> left  = new ArrayDeque<>();
    private final Deque<Long> right = new ArrayDeque<>();

    public CpsHud() { super("CPS", 0.01f, 0.04f); }

    @SubscribeEvent
    public void onMouseClick(InputEvent.RawMouseEvent e) {
        long now = System.currentTimeMillis();
        if (e.getButton() == 0) left.addLast(now);
        if (e.getButton() == 1) right.addLast(now);
    }

    private int cps(Deque<Long> q) {
        long cut = System.currentTimeMillis() - 1000;
        q.removeIf(t -> t < cut);
        return q.size();
    }

    @Override
    public void render(MatrixStack ms, float pt) {
        String text = "CPS: " + cps(left) + " | " + cps(right);
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
