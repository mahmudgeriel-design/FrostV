package com.frostvisuals.features.world;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.IntSetting;
public class TimeControl extends Feature {
    public final IntSetting time = new IntSetting("Time", 6000, 0, 24000);
    public TimeControl() { super("Time Control", Category.WORLD); settings.add(time); }
}
