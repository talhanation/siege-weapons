package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.entities.TransportCartEntity;
import com.talhanation.siegeweapons.entities.projectile.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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

    public static final RegistryObject<EntityType<BallistaEntity>> BALLISTA = ENTITY_TYPES.register("ballista",
            () -> EntityType.Builder.of(BallistaEntity::new, MobCategory.MISC)
                    .sized(2.0F, 2.0F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(Main.MOD_ID, "ballista").toString()));

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

    public static final RegistryObject<EntityType<CatapultCobbleClusterProjectile>> COBBLE_CLUSTER_PROJECTILE = ENTITY_TYPES.register("catapult_cluster_projectile",
            () -> EntityType.Builder.of(CatapultCobbleClusterProjectile::factory, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(3)
                    .updateInterval(20)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult_cluster_projectile").toString()));

    public static final RegistryObject<EntityType<FirePotProjectile>> FIRE_POT_PROJECTILE = ENTITY_TYPES.register("catapult_fire_pot_projectile",
            () -> EntityType.Builder.of(FirePotProjectile::factory, MobCategory.MISC)
                    .sized(0.85F, 0.85F)
                    .clientTrackingRange(3)
                    .updateInterval(20)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult_fire_pot_projectile").toString()));

    public static final RegistryObject<EntityType<ExplosionPotProjectile>> EXPLOSION_POT_PROJECTILE = ENTITY_TYPES.register("catapult_explosion_pot_projectile",
            () -> EntityType.Builder.of(ExplosionPotProjectile::factory, MobCategory.MISC)
                    .sized(0.85F, 0.85F)
                    .clientTrackingRange(3)
                    .updateInterval(20)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult_explosion_pot_projectile").toString()));

    public static final RegistryObject<EntityType<BallistaProjectile>> BALLISTA_PROJECTILE = ENTITY_TYPES.register("ballista_projectile",
            () -> EntityType.Builder.of(BallistaProjectile::factory, MobCategory.MISC)
                    .sized(0.85F, 0.85F)
                    .clientTrackingRange(3)
                    .build(new ResourceLocation(Main.MOD_ID, "ballista_projectile").toString()));

}
