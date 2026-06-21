package com.frostvisuals;

import com.frostvisuals.config.FrostConfig;
import com.frostvisuals.features.FeatureManager;
import com.frostvisuals.gui.FrostGui;
import com.frostvisuals.hud.HudRenderer;
import com.frostvisuals.keys.KeyBindings;
import com.frostvisuals.waypoints.WaypointManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("frostvisuals")
public class FrostVisuals {

    public static final String MOD_ID = "frostvisuals";
    public static final String MOD_NAME = "FrostVisuals";
    public static final String VERSION = "2.0.0";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static FrostVisuals INSTANCE;
    public static FeatureManager featureManager;
    public static WaypointManager waypointManager;
    public static FrostGui gui;
    public static HudRenderer hudRenderer;

    public FrostVisuals() {
        INSTANCE = this;
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        FrostConfig.load();
        featureManager = new FeatureManager();
        waypointManager = new WaypointManager();
        featureManager.registerAll();
        gui = new FrostGui();
        hudRenderer = new HudRenderer();
        KeyBindings.register();
        LOGGER.info("\u2744 FrostVisuals {} loaded with {} features!", VERSION, featureManager.getFeatures().size());
    }
}
