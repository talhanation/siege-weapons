package com.talhanation.siegeweapons.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class SiegeWeaponsServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SERVER;

    public static ForgeConfigSpec.IntValue catapultDamage;
    public static ForgeConfigSpec.IntValue ballistaDamage;
    public static ForgeConfigSpec.IntValue fireBallistaDamage;

    static {
          BUILDER.comment("Siege Weapons Config:").push("Siege Weapons");

        catapultDamage = BUILDER.comment("""
                        
                        Damage a catapult projectile makes.
                        \t(takes effect after restart)
                        \tdefault: 20""")
                .worldRestart()
                .defineInRange("catapultDamage", 20, 0, 1453);

        ballistaDamage = BUILDER.comment("""
                        
                        Damage a ballista projectile makes.
                        \t(takes effect after restart)
                        \tdefault: 10""")
                .worldRestart()
                .defineInRange("ballistaDamage", 10, 0, 1453);

        fireBallistaDamage = BUILDER.comment("""
                        
                        Damage a fire ballista projectile makes.
                        \t(takes effect after restart)
                        \tdefault: 12""")
                .worldRestart()
                .defineInRange("ballistaDamage", 12, 0, 1453);


        //Village Config
        /*
        BUILDER.pop();
        BUILDER.comment("Siege Weapons Village Config:").push("Villages");

        RecruitTablesPOIReleasing = BUILDER.comment("""
                        
                        Should Villager Recruits that were created with Tables release the POI for other Villagers?
                        True -> allows multiple villagers to become a recruit with one table.
                        False -> only one villager can become a recruit with one table.
                        \t(takes effect after restart)
                        \tdefault: true""")
                .worldRestart()
                .define("RecruitTablesPOIReleasing", true);

          */
        SERVER = BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        spec.setConfig(configData);
    }
}

