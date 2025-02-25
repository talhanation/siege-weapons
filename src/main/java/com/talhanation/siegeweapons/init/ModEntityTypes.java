package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.entities.TransportCartEntity;
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
                    .sized(3.0F, 3.0F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(Main.MOD_ID, "catapult").toString()));

    public static final RegistryObject<EntityType<TransportCartEntity>> TRANSPORT_CART = ENTITY_TYPES.register("transportcart",
            () -> EntityType.Builder.of(TransportCartEntity::new, MobCategory.MISC)
                    .sized(3.0F, 3.0F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(Main.MOD_ID, "transportcart").toString()));

}
