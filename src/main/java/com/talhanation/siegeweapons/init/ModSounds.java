package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =  DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);

    public static final RegistryObject<SoundEvent> CATAPULT_SHOT = SOUNDS.register("catapult_shot",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID,"catapult_shot")));
    public static final RegistryObject<SoundEvent> TREBUCHET_SHOT = SOUNDS.register("trebuchet_shot",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID,"trebuchet_shot")));
    public static final RegistryObject<SoundEvent> BALLISTA_SHOT = SOUNDS.register("ballista_shot",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID,"ballista_shot")));
    public static final RegistryObject<SoundEvent> CATAPULT_LOADED = SOUNDS.register("catapult_loaded",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID,"catapult_loaded")));
    public static final RegistryObject<SoundEvent> BALLISTA_LOADED= SOUNDS.register("ballista_loaded",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID,"ballista_loaded")));
    public static final RegistryObject<SoundEvent> SIEGEWEAPON_HIT = SOUNDS.register("siegeweapon_hit",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID,"siegeweapon_hit")));

    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_0 = SOUNDS.register("catapult_draw_0",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_0")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_1 = SOUNDS.register("catapult_draw_1",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_1")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_2 = SOUNDS.register("catapult_draw_2",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_2")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_3 = SOUNDS.register("catapult_draw_3",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_3")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_4 = SOUNDS.register("catapult_draw_4",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_4")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_5 = SOUNDS.register("catapult_draw_5",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_5")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_6 = SOUNDS.register("catapult_draw_6",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_6")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_7 = SOUNDS.register("catapult_draw_7",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_7")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_8 = SOUNDS.register("catapult_draw_8",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_8")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_9 = SOUNDS.register("catapult_draw_9",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_9")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_10 = SOUNDS.register("catapult_draw_10",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_10")));
    public static final RegistryObject<SoundEvent> CATAPULT_DRAW_11 = SOUNDS.register("catapult_draw_11",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "catapult_draw_11")));

    public static final RegistryObject<SoundEvent> BALLISTA_DRAW_0 = SOUNDS.register("ballista_draw_0",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "ballista_draw_0")));
    public static final RegistryObject<SoundEvent> BALLISTA_DRAW_1 = SOUNDS.register("ballista_draw_1",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "ballista_draw_1")));
    public static final RegistryObject<SoundEvent> BALLISTA_DRAW_2 = SOUNDS.register("ballista_draw_2",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "ballista_draw_2")));
    public static final RegistryObject<SoundEvent> BALLISTA_DRAW_3 = SOUNDS.register("ballista_draw_3",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "ballista_draw_3")));
    public static final RegistryObject<SoundEvent> BALLISTA_DRAW_4 = SOUNDS.register("ballista_draw_4",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "ballista_draw_4")));
    public static final RegistryObject<SoundEvent> BALLISTA_DRAW_5 = SOUNDS.register("ballista_draw_5",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Main.MOD_ID, "ballista_draw_5")));

}
