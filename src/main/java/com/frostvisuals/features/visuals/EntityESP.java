package com.frostvisuals.features.visuals;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ColorSetting;
import com.frostvisuals.features.settings.ModeSetting;
import java.awt.Color;

public class EntityESP extends Feature {
    public final ModeSetting mode = new ModeSetting("Mode", "Box", "Box", "Corner", "Glow");
    public final ColorSetting espColor = new ColorSetting("Color", new Color(255, 0, 0, 200));
    public EntityESP() { super("Entity ESP", Category.VISUALS); settings.add(mode); settings.add(espColor); }
}
