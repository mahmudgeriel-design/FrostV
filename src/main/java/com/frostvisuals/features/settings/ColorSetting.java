package com.frostvisuals.features.settings;

import java.awt.Color;

public class ColorSetting extends Setting<Color> {
    private boolean rainbow;
    public ColorSetting(String name, Color defaultValue) { super(name, defaultValue); }
    public boolean isRainbow() { return rainbow; }
    public void setRainbow(boolean rainbow) { this.rainbow = rainbow; }
    public int getARGB() { return value.getRGB(); }
}
