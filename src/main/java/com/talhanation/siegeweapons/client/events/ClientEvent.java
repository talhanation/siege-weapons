package com.talhanation.siegeweapons.client.events;


import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.render.*;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD , value = Dist.CLIENT)
public class ClientEvent {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void entityRenderersEvent(EntityRenderersEvent.RegisterRenderers event){
        EntityRenderers.register(ModEntityTypes.CATAPULT.get(), CatapultRenderer::new);
        EntityRenderers.register(ModEntityTypes.TRANSPORT_CART.get(), TransportCartRenderer::new);
        EntityRenderers.register(ModEntityTypes.CATAPULT_PROJECTILE.get(), CatapultProjectileRenderer::new);
        EntityRenderers.register(ModEntityTypes.CATAPULT_BUNDLE_PROJECTILE.get(), CatapultBundleProjectileRenderer::new);
        EntityRenderers.register(ModEntityTypes.CATAPULT_EXPLOSION_POT_PROJECTILE.get(), CatapultExplosionPotProjectileRenderer::new);
        EntityRenderers.register(ModEntityTypes.CATAPULT_FIRE_POT_PROJECTILE.get(), CatapultFirePotProjectileRenderer::new);

    }

    @Nullable
    public static Entity getEntityByLooking() {
        HitResult hit = Minecraft.getInstance().hitResult;

        if (hit instanceof EntityHitResult entityHitResult){
            return entityHitResult.getEntity();
        }
        return null;
    }
}