package com.frostvisuals.features.sounds;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.FloatSetting;
import com.frostvisuals.features.settings.ModeSetting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundEvents;

public class HitSound extends Feature {
    public final FloatSetting volume = new FloatSetting("Volume", 1f, 0.1f, 2f);
    public final FloatSetting pitch  = new FloatSetting("Pitch",  1f, 0.5f, 2f);
    public HitSound() { super("Hit Sound", Category.SOUNDS); settings.add(volume); settings.add(pitch); }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || e.getSource().getEntity() != mc.player) return;
        mc.player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, volume.getValue(), pitch.getValue());
    }
}
