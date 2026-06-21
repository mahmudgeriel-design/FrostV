package com.frostvisuals.features.visuals;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ColorSetting;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.RenderHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;

public class Chams extends Feature {
    public final ColorSetting chamColor = new ColorSetting("Color", new Color(255, 0, 0, 150));
    public Chams() { super("Chams", Category.VISUALS); settings.add(chamColor); }

    public void preRender(MatrixStack ms) {
        RenderSystem.disableDepthTest();
    }
    public void postRender(MatrixStack ms) {
        RenderSystem.enableDepthTest();
    }
}
