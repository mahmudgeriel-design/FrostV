package com.frostvisuals.features.pvp;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ModeSetting;
public class KillEffect extends Feature {
    public final ModeSetting mode = new ModeSetting("Mode", "Explode", "Explode", "Lightning", "Firework");
    public KillEffect() { super("Kill Effect", Category.PVP); settings.add(mode); }
}
