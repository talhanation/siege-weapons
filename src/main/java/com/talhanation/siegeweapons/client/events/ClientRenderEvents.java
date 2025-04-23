package com.talhanation.siegeweapons.client.events;

import com.talhanation.siegeweapons.entities.BallistaEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientRenderEvents {
    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        if (Minecraft.getInstance().player.getVehicle() instanceof BallistaEntity ballista && ballista.getShowTrajectory()) {
            event.setCanceled(true);
        }
    }
}
