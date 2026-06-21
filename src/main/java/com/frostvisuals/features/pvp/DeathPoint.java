package com.frostvisuals.features.pvp;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.waypoints.Waypoint;
import com.frostvisuals.FrostVisuals;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import java.awt.Color;

public class DeathPoint extends Feature {
    public DeathPoint() { super("Death Point", Category.PVP); }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || e.getEntity() != mc.player) return;
        Waypoint wp = new Waypoint("Death", mc.player.blockPosition(), Color.RED.getRGB(), "overworld");
        FrostVisuals.waypointManager.add(wp);
    }
}
