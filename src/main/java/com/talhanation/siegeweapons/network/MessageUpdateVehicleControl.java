package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;


public class MessageUpdateVehicleControl implements Message<MessageUpdateVehicleControl> {

    private boolean forward, backward, left, right;
    private UUID uuid;

    public MessageUpdateVehicleControl() {
    }

    public MessageUpdateVehicleControl(boolean forward, boolean backward, boolean left, boolean right, UUID driver) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
        this.uuid = driver;
    }

    @Override
    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        if (!context.getSender().getUUID().equals(uuid)) {
            //Main.LOGGER.error("The UUID of the sender was not equal to the packet UUID");
            return;
        }

        Entity entity = context.getSender().getVehicle();

        if (!(entity instanceof AbstractVehicleEntity vehicle)) {
            return;
        }

        vehicle.updateControls(forward, backward, left, right, context.getSender());
    }

    @Override
    public MessageUpdateVehicleControl fromBytes(FriendlyByteBuf buf) {
        this.forward = buf.readBoolean();
        this.backward = buf.readBoolean();
        this.left = buf.readBoolean();
        this.right = buf.readBoolean();
        this.uuid = buf.readUUID();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(forward);
        buf.writeBoolean(backward);
        buf.writeBoolean(left);
        buf.writeBoolean(right);
        buf.writeUUID(uuid);
    }

}
