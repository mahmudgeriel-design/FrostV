package com.frostvisuals.features.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockHud extends HudElement {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    public ClockHud() { super("Clock", 0.01f, 0.28f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        String text = "Time: " + LocalTime.now().format(FMT);
        width  = font().width(text);
        height = font().lineHeight;
        drawText(ms, text, getRenderX(), getRenderY());
    }
}
