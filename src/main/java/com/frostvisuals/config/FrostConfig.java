package com.frostvisuals.config;

import com.frostvisuals.FrostVisuals;
import com.google.gson.*;
import net.minecraft.client.Minecraft;
import java.io.*;
import java.nio.file.*;

public class FrostConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Path configFile;

    public static void load() {
        try {
            configFile = Minecraft.getInstance().gameDirectory.toPath().resolve("config/frostvisuals.json");
            Files.createDirectories(configFile.getParent());
            if (!Files.exists(configFile)) { save(); return; }
            String json = new String(Files.readAllBytes(configFile));
            JsonObject obj = GSON.fromJson(json, JsonObject.class);
            if (obj == null) return;
            if (FrostVisuals.featureManager == null) return;
            FrostVisuals.featureManager.getFeatures().forEach(f -> {
                if (obj.has(f.getName())) {
                    JsonObject fo = obj.getAsJsonObject(f.getName());
                    if (fo.has("enabled")) f.setEnabled(fo.get("enabled").getAsBoolean());
                }
            });
        } catch (Exception e) {
            FrostVisuals.LOGGER.warn("Failed to load config: {}", e.getMessage());
        }
    }

    public static void save() {
        try {
            if (configFile == null) return;
            JsonObject root = new JsonObject();
            if (FrostVisuals.featureManager != null) {
                FrostVisuals.featureManager.getFeatures().forEach(f -> {
                    JsonObject fo = new JsonObject();
                    fo.addProperty("enabled", f.isEnabled());
                    root.add(f.getName(), fo);
                });
            }
            Files.write(configFile, GSON.toJson(root).getBytes());
        } catch (Exception e) {
            FrostVisuals.LOGGER.warn("Failed to save config: {}", e.getMessage());
        }
    }
}
