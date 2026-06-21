package com.frostvisuals.features.pvp;

import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ModeSetting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;

public class HitEffect extends Feature {
    public final ModeSetting mode = new ModeSetting("Mode", "Crit", "Crit", "Heart", "Smoke");
    public HitEffect() { super("Hit Effect", Category.PVP); settings.add(mode); }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;
        if (e.getSource().getEntity() != mc.player) return;
        Vector3d pos = e.getEntity().position();
        if (mode.is("Crit")) mc.level.addParticle(ParticleTypes.CRIT, pos.x, pos.y+1, pos.z, 0, 0, 0);
        else if (mode.is("Heart")) mc.level.addParticle(ParticleTypes.HEART, pos.x, pos.y+1, pos.z, 0, 0, 0);
        else mc.level.addParticle(ParticleTypes.SMOKE, pos.x, pos.y+1, pos.z, 0, 0, 0);
    }
}
