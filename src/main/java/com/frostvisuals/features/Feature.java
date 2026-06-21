package com.frostvisuals.features;

import com.frostvisuals.features.settings.Setting;
import net.minecraftforge.common.MinecraftForge;
import java.util.ArrayList;
import java.util.List;

public abstract class Feature {
    private final String name;
    private final Category category;
    private boolean enabled;
    protected final List<Setting<?>> settings = new ArrayList<>();

    public Feature(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            MinecraftForge.EVENT_BUS.register(this);
            onEnable();
        } else {
            MinecraftForge.EVENT_BUS.unregister(this);
            onDisable();
        }
    }

    public void toggle() { setEnabled(!enabled); }
    public void onEnable() {}
    public void onDisable() {}

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public List<Setting<?>> getSettings() { return settings; }
}
