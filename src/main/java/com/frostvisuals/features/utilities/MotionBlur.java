package com.frostvisuals.features.utilities;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.FloatSetting;
public class MotionBlur extends Feature {
    public final FloatSetting strength = new FloatSetting("Strength", 0.5f, 0.1f, 0.9f);
    public MotionBlur() { super("Motion Blur", Category.UTILITIES); settings.add(strength); }
}
