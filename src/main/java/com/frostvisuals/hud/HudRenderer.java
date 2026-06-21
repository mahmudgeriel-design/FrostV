package com.frostvisuals.hud;

import com.frostvisuals.FrostVisuals;
import com.frostvisuals.features.Feature;
import com.frostvisuals.features.hud.HudElement;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HudRenderer {

    public HudRenderer() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        for (Feature f : FrostVisuals.featureManager.getFeatures()) {
            if (f instanceof HudElement && f.isEnabled()) {
                ((HudElement) f).render(event.getMatrixStack(), event.getPartialTicks());
            }
        }
    }
}
