package com.talhanation.siegeweapons.client.events;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.entities.IShootingWeapon;
import com.talhanation.siegeweapons.init.ModItems;
import com.talhanation.siegeweapons.network.MessageLoadAndShootWeapon;
import com.talhanation.siegeweapons.network.MessageSetCatapultRange;
import com.talhanation.siegeweapons.network.MessageTryLoadFromHand;
import com.talhanation.siegeweapons.network.MessageUpdateVehicleControl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)

public class KeyEvents {
    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer clientPlayerEntity = minecraft.player;
        boolean pressedJumpKey = minecraft.options.keyJump.isDown();
        boolean forwardKey = minecraft.options.keyUp.isDown();
        boolean backwardKey = minecraft.options.keyDown.isDown();
        boolean leftKey = minecraft.options.keyLeft.isDown();
        boolean rightKey = minecraft.options.keyRight.isDown();


        if (clientPlayerEntity == null)
            return;

        if (clientPlayerEntity.getVehicle() instanceof AbstractVehicleEntity vehicle) {
            Main.SIMPLE_CHANNEL.sendToServer(new MessageUpdateVehicleControl(forwardKey, backwardKey, leftKey, rightKey, clientPlayerEntity.getUUID()));

            if(vehicle instanceof IShootingWeapon){
                Main.SIMPLE_CHANNEL.sendToServer(new MessageLoadAndShootWeapon(pressedJumpKey, clientPlayerEntity.getUUID()));
            }

        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseButton event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer clientPlayerEntity = minecraft.player;

        if(clientPlayerEntity == null) return;

        boolean rightClick = event.getButton() == 1 && event.getAction() == GLFW.GLFW_PRESS;

        if (rightClick && clientPlayerEntity.getVehicle() instanceof IShootingWeapon weapon) {
            if(weapon instanceof BallistaEntity ballista){
                boolean isState = ballista.getState() == BallistaEntity.BallistaState.LOADED;
                boolean hand = clientPlayerEntity.getMainHandItem().getItem() == ModItems.BALLISTA_PROJECTILE_ITEM.get();

                if(hand && isState){
                    Main.SIMPLE_CHANNEL.sendToServer(new MessageTryLoadFromHand());
                    return;
                }

            }
            weapon.setShowTrajectory(true);

            event.setCanceled(true);
        }

        boolean rightRelease = event.getButton() == 1 && event.getAction() == GLFW.GLFW_RELEASE;

        if (rightRelease && clientPlayerEntity.getVehicle() instanceof IShootingWeapon weapon) {
            weapon.setShowTrajectory(false);
        }
    }


    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseScrollingEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer clientPlayerEntity = minecraft.player;
        double scrollDelta = event.getScrollDelta();

        if (clientPlayerEntity.getVehicle() instanceof CatapultEntity catapult && catapult.getShowTrajectory()) {
            Main.SIMPLE_CHANNEL.sendToServer(new MessageSetCatapultRange((int) scrollDelta * 2));
            event.setCanceled(true);
        }

        if (clientPlayerEntity == null) return;
    }

}
