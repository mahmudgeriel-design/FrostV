package com.frostvisuals.features;

public enum Category {
    HUD("HUD"),
    PVP("PvP"),
    VISUALS("Visuals"),
    TRAILS("Trails"),
    WORLD("World"),
    SOUNDS("Sounds"),
    UTILITIES("Utilities");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}
