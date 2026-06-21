package com.frostvisuals.keys;

import com.frostvisuals.FrostVisuals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyBinding openGui;
    public static KeyBinding openHudEditor;
    public static KeyBinding openWaypoints;
    public static KeyBinding toggleFeature;

    public static void register() {
        openGui = new KeyBinding("key.frostvisuals.gui", GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.frostvisuals");
        openHudEditor = new KeyBinding("key.frostvisuals.hud", GLFW.GLFW_KEY_H, "key.categories.frostvisuals");
        openWaypoints = new KeyBinding("key.frostvisuals.waypoints", GLFW.GLFW_KEY_B, "key.categories.frostvisuals");
        ClientRegistry.registerKeyBinding(openGui);
        ClientRegistry.registerKeyBinding(openHudEditor);
        ClientRegistry.registerKeyBinding(openWaypoints);
        MinecraftForge.EVENT_BUS.register(KeyBindings.class);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.screen != null) return;
        if (openGui.consumeClick()) mc.setScreen(FrostVisuals.gui);
        if (openHudEditor.consumeClick()) mc.setScreen(new com.frostvisuals.gui.HudEditorScreen());
        if (openWaypoints.consumeClick()) mc.setScreen(new com.frostvisuals.gui.WaypointScreen());
    }
}
