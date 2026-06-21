package com.frostvisuals.features.sounds;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;

public class KillSound extends Feature {
    public KillSound() { super("Kill Sound", Category.SOUNDS); }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || e.getSource().getEntity() != mc.player) return;
        if (e.getEntity() instanceof PlayerEntity)
            mc.player.playSound(SoundEvents.PLAYER_LEVELUP, 1f, 1f);
    }
}
