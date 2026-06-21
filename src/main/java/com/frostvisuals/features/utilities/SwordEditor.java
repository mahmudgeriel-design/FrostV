package com.frostvisuals.features.utilities;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.FloatSetting;
public class SwordEditor extends Feature {
    public final FloatSetting posX = new FloatSetting("Pos X", 0f, -1f, 1f);
    public final FloatSetting posY = new FloatSetting("Pos Y", 0f, -1f, 1f);
    public final FloatSetting scale = new FloatSetting("Scale", 1f, 0.5f, 2f);
    public SwordEditor() {
        super("Sword Editor", Category.UTILITIES);
        settings.add(posX); settings.add(posY); settings.add(scale);
    }
}
