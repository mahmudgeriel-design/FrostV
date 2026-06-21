package com.frostvisuals.utils;

import java.awt.Color;

public class ColorUtils {

    /** Rainbow colour cycling over the given period in ms */
    public static int rainbow(long time, long period) {
        float hue = (time % period) / (float) period;
        return Color.HSBtoRGB(hue, 1f, 1f) | 0xFF000000;
    }

    /** Pulse between two colours using a sine wave */
    public static int pulse(int colA, int colB, long time, long period) {
        float t = (float)(0.5 + 0.5 * Math.sin(2 * Math.PI * time / period));
        return lerpColor(colA, colB, t);
    }

    public static int lerpColor(int a, int b, float t) {
        int ar = (a >> 16) & 0xFF, ag = (a >> 8) & 0xFF, ab = a & 0xFF, aa = (a >> 24) & 0xFF;
        int br = (b >> 16) & 0xFF, bg = (b >> 8) & 0xFF, bb = b & 0xFF, ba = (b >> 24) & 0xFF;
        int r = (int)(ar + (br - ar) * t);
        int g = (int)(ag + (bg - ag) * t);
        int bv= (int)(ab + (bb - ab) * t);
        int al= (int)(aa + (ba - aa) * t);
        return (al << 24) | (r << 16) | (g << 8) | bv;
    }

    public static int withAlpha(int color, int alpha) {
        return (color & 0x00FFFFFF) | (alpha << 24);
    }
}
