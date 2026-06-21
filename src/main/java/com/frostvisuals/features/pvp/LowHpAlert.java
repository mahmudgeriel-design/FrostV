package com.frostvisuals.features.pvp;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.FloatSetting;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

public class LowHpAlert extends Feature {
    public final FloatSetting threshold = new FloatSetting("HP Threshold", 6f, 1f, 20f);
    public LowHpAlert() { super("Low HP Alert", Category.PVP); settings.add(threshold); }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || e.phase != TickEvent.Phase.END) return;
        if (mc.player.getHealth() <= threshold.getValue()) {
            mc.player.displayClientMessage(new StringTextComponent("\u00a7c\u2665 LOW HP!"), true);
        }
    }
}
