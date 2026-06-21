package com.frostvisuals.features.settings;

public class ModeSetting extends Setting<String> {
    private final String[] modes;
    public ModeSetting(String name, String defaultMode, String... modes) {
        super(name, defaultMode);
        this.modes = modes;
    }
    public String[] getModes() { return modes; }
    public boolean is(String mode) { return value.equalsIgnoreCase(mode); }
}
