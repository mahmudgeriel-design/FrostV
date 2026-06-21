package com.frostvisuals.features.world;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.FloatSetting;
public class FogControl extends Feature {
    public final FloatSetting density = new FloatSetting("Density", 0f, 0f, 1f);
    public FogControl() { super("Fog Control", Category.WORLD); settings.add(density); }
}
