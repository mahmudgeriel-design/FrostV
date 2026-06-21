package com.frostvisuals.features.visuals;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;

public class FullBright extends Feature {
    private float prevGamma;
    public FullBright() { super("Full Bright", Category.VISUALS); }

    @Override public void onEnable() { prevGamma = Minecraft.getInstance().options.gamma; }
    @Override public void onDisable() { Minecraft.getInstance().options.gamma = prevGamma; }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.START) Minecraft.getInstance().options.gamma = 100f;
    }
}
