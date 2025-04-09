package com.talhanation.siegeweapons;

import com.talhanation.siegeweapons.entities.BallistaEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerSideEvents {
    @SubscribeEvent
    public void onEntityMount(EntityMountEvent event) {
        if (event.getEntityBeingMounted() instanceof BallistaEntity ballista && event.getEntityMounting() instanceof LivingEntity living) {
            living.setYRot(ballista.getYRot());
            living.setXRot(ballista.getXRot());
            living.yHeadRot = ballista.getYRot();
        }
    }
}
