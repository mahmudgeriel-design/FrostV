package com.frostvisuals.features.trails;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ColorSetting;
import com.frostvisuals.features.settings.ModeSetting;
import java.awt.Color;
public class PlayerTrail extends Feature {
    public final ModeSetting mode = new ModeSetting("Mode","Particle","Particle","Line","Block");
    public final ColorSetting trailColor = new ColorSetting("Color", new Color(0,200,255,200));
    public PlayerTrail() { super("Player Trail", Category.TRAILS); settings.add(mode); settings.add(trailColor); }
}
