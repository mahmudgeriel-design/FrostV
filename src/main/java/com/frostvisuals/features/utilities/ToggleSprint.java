package com.frostvisuals.features.utilities;
import com.frostvisuals.features.Category;
import com.frostvisuals.features.Feature;
import net.minecraft.entity.player.PlayerEntity;

public class ToggleSprint extends Feature {
    public ToggleSprint() { super("Toggle Sprint", Category.UTILITIES); }

    public void onPlayerTick(PlayerEntity player) {
        if (!player.isCrouching() && !player.isSprinting()) player.setSprinting(true);
    }
}
