package com.frostvisuals.features.pvp;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class KillStreak extends Feature {
    private int streak;
    public KillStreak() { super("Kill Streak", Category.PVP); }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (e.getSource().getEntity() == mc.player && e.getEntity() instanceof PlayerEntity) {
            streak++;
            if (streak > 1) mc.player.displayClientMessage(new StringTextComponent("\u00a7c\u2605 Kill streak: " + streak), true);
        }
        if (e.getEntity() == mc.player) streak = 0;
    }
}
