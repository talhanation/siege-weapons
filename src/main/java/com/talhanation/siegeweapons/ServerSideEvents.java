package com.talhanation.siegeweapons;

import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.checkerframework.checker.units.qual.C;

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
