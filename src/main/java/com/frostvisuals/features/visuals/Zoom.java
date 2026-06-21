package com.frostvisuals.features.visuals;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.FloatSetting;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class Zoom extends Feature {
    public final FloatSetting zoomLevel = new FloatSetting("Zoom Level", 4f, 1.5f, 10f);
    private boolean zooming;

    public Zoom() { super("Zoom", Category.VISUALS); settings.add(zoomLevel); }

    public boolean isZooming() {
        return isEnabled() && GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_C) == GLFW.GLFW_PRESS;
    }

    public Double getZoomFov(Double original) {
        return original / zoomLevel.getValue();
    }
}
