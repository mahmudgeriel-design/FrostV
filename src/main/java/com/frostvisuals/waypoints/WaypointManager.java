package com.frostvisuals.waypoints;

import com.frostvisuals.FrostVisuals;
import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WaypointManager {
    private final List<Waypoint> waypoints = new ArrayList<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private Path file;

    public WaypointManager() {
        file = Minecraft.getInstance().gameDirectory.toPath().resolve("config/frostvisuals_waypoints.json");
        load();
    }

    public void add(Waypoint w) { waypoints.add(w); save(); }
    public void remove(Waypoint w) { waypoints.remove(w); save(); }
    public List<Waypoint> getWaypoints() { return Collections.unmodifiableList(waypoints); }

    public void load() {
        try {
            if (!Files.exists(file)) return;
            String json = new String(Files.readAllBytes(file));
            JsonArray arr = GSON.fromJson(json, JsonArray.class);
            if (arr == null) return;
            waypoints.clear();
            for (JsonElement el : arr) {
                JsonObject o = el.getAsJsonObject();
                waypoints.add(new Waypoint(
                    o.get("name").getAsString(),
                    new BlockPos(o.get("x").getAsInt(), o.get("y").getAsInt(), o.get("z").getAsInt()),
                    o.get("color").getAsInt(),
                    o.has("dim") ? o.get("dim").getAsString() : "overworld"
                ));
            }
        } catch (Exception e) { FrostVisuals.LOGGER.warn("Waypoint load error: {}", e.getMessage()); }
    }

    public void save() {
        try {
            Files.createDirectories(file.getParent());
            JsonArray arr = new JsonArray();
            for (Waypoint w : waypoints) {
                JsonObject o = new JsonObject();
                o.addProperty("name", w.getName());
                o.addProperty("x", w.getPos().getX());
                o.addProperty("y", w.getPos().getY());
                o.addProperty("z", w.getPos().getZ());
                o.addProperty("color", w.getColor());
                o.addProperty("dim", w.getDimension());
                arr.add(o);
            }
            Files.write(file, GSON.toJson(arr).getBytes());
        } catch (Exception e) { FrostVisuals.LOGGER.warn("Waypoint save error: {}", e.getMessage()); }
    }
}
