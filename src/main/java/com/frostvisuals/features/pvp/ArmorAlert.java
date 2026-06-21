package com.frostvisuals.features.pvp;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.settings.IntSetting;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

public class ArmorAlert extends Feature {
    public final IntSetting threshold = new IntSetting("Threshold %", 20, 1, 50);
    public ArmorAlert() { super("Armor Alert", Category.PVP); settings.add(threshold); }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || e.phase != TickEvent.Phase.END) return;
        for (ItemStack stack : mc.player.getArmorSlots()) {
            if (stack.isEmpty()) continue;
            if (stack.getMaxDamage() > 0) {
                float pct = 100f * (stack.getMaxDamage() - stack.getDamageValue()) / stack.getMaxDamage();
                if (pct <= threshold.getValue()) {
                    mc.player.displayClientMessage(new StringTextComponent("\u00a7c\u26a0 " + stack.getDisplayName().getString() + " low!"), true);
                }
            }
        }
    }
}
