package com.frostvisuals.features;

import com.frostvisuals.features.hud.*;
import com.frostvisuals.features.pvp.*;
import com.frostvisuals.features.visuals.*;
import com.frostvisuals.features.trails.*;
import com.frostvisuals.features.world.*;
import com.frostvisuals.features.sounds.*;
import com.frostvisuals.features.utilities.*;
import java.util.*;

public class FeatureManager {
    private final List<Feature> features = new ArrayList<>();

    public void registerAll() {
        // HUD
        add(new FpsHud());
        add(new CpsHud());
        add(new PingHud());
        add(new CoordinatesHud());
        add(new SpeedHud());
        add(new BpsHud());
        add(new DirectionHud());
        add(new BiomeHud());
        add(new ChunkHud());
        add(new ClockHud());
        add(new ArmorHud());
        add(new PotionHud());
        add(new TargetHud());
        add(new KeystrokesHud());
        add(new ComboHud());
        add(new KillDeathHud());
        add(new ReachHud());
        add(new NearPlayersHud());
        add(new WaypointsHud());
        add(new DeathLogHud());
        add(new PingGraphHud());
        add(new CpsGraphHud());
        add(new AttackIndicatorHud());
        add(new ScoreboardHud());
        // PvP
        add(new HitColor());
        add(new HitEffect());
        add(new HitParticles());
        add(new KillEffect());
        add(new KillStreak());
        add(new ArmorAlert());
        add(new LowHpAlert());
        add(new TotemTracker());
        add(new PearlCooldown());
        add(new ShieldCooldown());
        add(new ChorusCooldown());
        add(new ComboDisplay());
        add(new DeathPoint());
        add(new AttackIndicatorFeature());
        add(new RhythmHelper());
        add(new SessionStats());
        add(new VelocityDisplay());
        add(new WTapIndicator());
        // Visuals
        add(new FullBright());
        add(new NoFire());
        add(new NoHurtCam());
        add(new Zoom());
        add(new Freelook());
        add(new Chams());
        add(new EntityESP());
        add(new NameTag());
        add(new HealthTag());
        add(new HitmarkerFeature());
        add(new PlayerTracer());
        add(new AuraRing());
        add(new ShadowClone());
        add(new StepParticles());
        // Trails
        add(new PlayerTrail());
        add(new ProjectileTrail());
        add(new ArrowTrajectory());
        add(new PearlLandingPredictor());
        // World
        add(new FogControl());
        add(new TimeControl());
        add(new NoWeather());
        add(new NetherFogOff());
        add(new CustomSky());
        add(new CustomVoidColor());
        add(new FallingLeaves());
        add(new FallingSnowflakes());
        add(new AmbientParticles());
        add(new LavaTransparency());
        // Sounds
        add(new HitSound());
        add(new KillSound());
        add(new GuiSounds());
        add(new TotemAnimation());
        // Utilities
        add(new ToggleSprint());
        add(new ToggleSneak());
        add(new NoBobbing());
        add(new MotionBlur());
        add(new OldAnimations());
        add(new ItemPhysics());
        add(new ItemBinds());
        add(new SwordEditor());
    }

    private void add(Feature f) { features.add(f); }

    public List<Feature> getFeatures() { return Collections.unmodifiableList(features); }

    public Feature getFeature(String name) {
        return features.stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Feature> getByCategory(Category category) {
        List<Feature> list = new ArrayList<>();
        for (Feature f : features) if (f.getCategory() == category) list.add(f);
        return list;
    }
}
