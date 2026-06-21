package com.frostvisuals.utils;

public class MathUtils {
    public static double lerp(double a, double b, double t) { return a + (b - a) * t; }
    public static float clamp(float v, float min, float max) { return Math.max(min, Math.min(max, v)); }
    public static double distanceTo(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1, dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
