package com.frostvisuals.features.utilities;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;

public class NoBobbing extends Feature {
    public NoBobbing() { super("No Bobbing", Category.UTILITIES); }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.START) Minecraft.getInstance().options.bobView = false;
    }
    @Override public void onDisable() { Minecraft.getInstance().options.bobView = true; }
}
