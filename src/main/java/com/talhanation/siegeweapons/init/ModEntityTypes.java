package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.entities.TransportCartEntity;
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleBundleProjectile;
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleProjectile;
import com.talhanation.siegeweapons.entities.projectile.CatapultExplosionPotProjectile;
import com.talhanation.siegeweapons.entities.projectile.CatapultFirePotProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Main.MOD_ID);

    public static final RegistryObject<EntityType<CatapultEntity>> CATAPULT = ENTITY_TYPES.register("catapult",
            () -> EntityType.Builder.of(CatapultEntity::new, MobCategory.MISC)
                    .sized(4.0F, 4.0F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult").toString()));

    public static final RegistryObject<EntityType<TransportCartEntity>> TRANSPORT_CART = ENTITY_TYPES.register("transport_cart",
            () -> EntityType.Builder.of(TransportCartEntity::new, MobCategory.MISC)
                    .sized(3.0F, 3.0F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(Main.MOD_ID, "transport_cart").toString()));
//PROJECTILES
    public static final RegistryObject<EntityType<CatapultCobbleProjectile>> CATAPULT_PROJECTILE = ENTITY_TYPES.register("catapult_projectile",
            () -> EntityType.Builder.of(CatapultCobbleProjectile::factory, MobCategory.MISC)
                    .sized(0.85F, 0.85F)
                    .clientTrackingRange(3)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult_projectile").toString()));

    public static final RegistryObject<EntityType<CatapultCobbleBundleProjectile>> CATAPULT_BUNDLE_PROJECTILE = ENTITY_TYPES.register("catapult_bundle_projectile",
            () -> EntityType.Builder.of(CatapultCobbleBundleProjectile::factory, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(3)
                    .updateInterval(20)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult_bundle_projectile").toString()));

    public static final RegistryObject<EntityType<CatapultFirePotProjectile>> CATAPULT_FIRE_POT_PROJECTILE = ENTITY_TYPES.register("catapult_fire_pot_projectile",
            () -> EntityType.Builder.of(CatapultFirePotProjectile::factory, MobCategory.MISC)
                    .sized(0.85F, 0.85F)
                    .clientTrackingRange(3)
                    .updateInterval(20)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult_fire_pot_projectile").toString()));

    public static final RegistryObject<EntityType<CatapultExplosionPotProjectile>> CATAPULT_EXPLOSION_POT_PROJECTILE = ENTITY_TYPES.register("catapult_explosion_pot_projectile",
            () -> EntityType.Builder.of(CatapultExplosionPotProjectile::factory, MobCategory.MISC)
                    .sized(0.85F, 0.85F)
                    .clientTrackingRange(3)
                    .updateInterval(20)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult_explosion_pot_projectile").toString()));

}
