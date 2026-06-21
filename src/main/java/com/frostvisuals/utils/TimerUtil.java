package com.frostvisuals.utils;

public class TimerUtil {
    private long lastMS = System.currentTimeMillis();

    public boolean hasElapsed(long ms) {
        return System.currentTimeMillis() - lastMS >= ms;
    }

    public void reset() { lastMS = System.currentTimeMillis(); }

    public long getElapsed() { return System.currentTimeMillis() - lastMS; }
}
