package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.entities.IShootingWeapon;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;


public class MessageLoadAndShootWeapon implements Message<MessageLoadAndShootWeapon> {
    boolean trigger;
    UUID uuid;
    public MessageLoadAndShootWeapon() {
    }

    public MessageLoadAndShootWeapon(boolean trigger, UUID uuid) {
        this.trigger = trigger;
        this.uuid = uuid;
    }

    @Override
    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        Entity entity = context.getSender().getVehicle();

        if (!(entity instanceof IShootingWeapon weapon)) {
            return;
        }

        weapon.updateTrigger(trigger, context.getSender());
        //weapon.shootWeapon();
    }

    @Override
    public MessageLoadAndShootWeapon fromBytes(FriendlyByteBuf buf) {
        this.trigger = buf.readBoolean();
        this.uuid = buf.readUUID();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.trigger);
        buf.writeUUID(this.uuid);
    }

}
