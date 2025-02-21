package com.talhanation.siegeweapons.client.events;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import com.talhanation.siegeweapons.entities.IShootingWeapon;
import com.talhanation.siegeweapons.network.MessageUpdateVehicleControl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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

            if (pressedJumpKey) {
                //Main.SIMPLE_CHANNEL.sendToServer(new MessageLoadAndShootWeapon(vehicle.getUUID()));
            }
        }

    }
}
