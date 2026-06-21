package com.frostvisuals.features.visuals;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ColorSetting;
import com.frostvisuals.features.settings.FloatSetting;
import java.awt.Color;

public class AuraRing extends Feature {
    public final FloatSetting radius = new FloatSetting("Radius", 3f, 1f, 6f);
    public final ColorSetting ringColor = new ColorSetting("Color", new Color(0, 150, 255, 180));
    public AuraRing() { super("Aura Ring", Category.VISUALS); settings.add(radius); settings.add(ringColor); }
}
