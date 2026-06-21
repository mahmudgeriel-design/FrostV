package com.frostvisuals.features.hud;

import com.frostvisuals.utils.RenderUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class KeystrokesHud extends HudElement {
    public KeystrokesHud() { super("Keystrokes", 0.82f, 0.82f); }

    @Override
    public void render(MatrixStack ms, float pt) {
        Minecraft mc = Minecraft.getInstance();
        int x = getRenderX(), y = getRenderY();
        width = 51; height = 36;
        boolean w = mc.options.keyUp.isDown();
        boolean a = mc.options.keyLeft.isDown();
        boolean s = mc.options.keyDown.isDown();
        boolean d = mc.options.keyRight.isDown();
        boolean lmb = mc.options.keyAttack.isDown();
        boolean rmb = mc.options.keyUse.isDown();
        boolean space = mc.options.keyJump.isDown();

        drawKey(ms, "W",     x + 17, y,      16, 12, w);
        drawKey(ms, "A",     x,      y + 13, 16, 12, a);
        drawKey(ms, "S",     x + 17, y + 13, 16, 12, s);
        drawKey(ms, "D",     x + 34, y + 13, 16, 12, d);
        drawKey(ms, "LMB",   x,      y + 26, 24, 9,  lmb);
        drawKey(ms, "RMB",   x + 27, y + 26, 24, 9,  rmb);
    }

    private void drawKey(MatrixStack ms, String label, int x, int y, int w, int h, boolean pressed) {
        int bg = pressed ? 0xCCFFFFFF : 0x88000000;
        int tc = pressed ? 0xFF000000 : 0xFFFFFFFF;
        RenderUtils.drawRect(ms, x, y, w, h, bg);
        RenderUtils.drawOutline(ms, x, y, w, h, 0xFFAAAAAA);
        int tx = x + (w - font().width(label)) / 2;
        int ty = y + (h - font().lineHeight) / 2;
        font().drawShadow(ms, label, tx, ty, tc);
    }
}
