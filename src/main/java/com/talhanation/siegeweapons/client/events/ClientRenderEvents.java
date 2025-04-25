package com.talhanation.siegeweapons.client.events;

import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ClientRenderEvents {
    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        if (Minecraft.getInstance().player.getVehicle() instanceof BallistaEntity ballista && ballista.getShowTrajectory()) {
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void onPlayerMountVehicle(EntityMountEvent event){
        Entity entity = event.getEntityBeingMounted();

        if(event.getEntityMounting() instanceof Player player){

            if(entity instanceof CatapultEntity catapult){
                if(event.isMounting()){
                    Minecraft.getInstance().options.setCameraType(CameraType.THIRD_PERSON_BACK);
                }
                else{
                    Minecraft.getInstance().options.setCameraType(CameraType.FIRST_PERSON);
                }
            }
        }
    }
}
