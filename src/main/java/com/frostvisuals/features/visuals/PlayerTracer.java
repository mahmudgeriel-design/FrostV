package com.frostvisuals.features.visuals;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ColorSetting;
import java.awt.Color;

public class PlayerTracer extends Feature {
    public final ColorSetting tracerColor = new ColorSetting("Color", new Color(255, 50, 50, 200));
    public PlayerTracer() { super("Player Tracer", Category.VISUALS); settings.add(tracerColor); }
}
