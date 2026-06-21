package com.frostvisuals.features.settings;

public class BoolSetting extends Setting<Boolean> {
    public BoolSetting(String name, boolean defaultValue) { super(name, defaultValue); }
    public boolean isEnabled() { return value; }
    public void toggle() { value = !value; }
}
