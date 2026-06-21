package com.frostvisuals.features.pvp;

import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.ColorSetting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import java.awt.Color;

public class HitColor extends Feature {
    public final ColorSetting hitColor = new ColorSetting("Hit Color", new Color(255, 0, 0, 180));
    public HitColor() { super("Hit Color", Category.PVP); settings.add(hitColor); }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent e) {
        if (Minecraft.getInstance().player == null) return;
        if (e.getSource().getEntity() == Minecraft.getInstance().player) {
            e.getEntity().invulnerableTime = 0;
        }
    }
}
