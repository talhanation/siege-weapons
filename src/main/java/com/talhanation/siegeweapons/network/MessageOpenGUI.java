package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.entities.AbstractInventoryVehicleEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;

public class MessageOpenGUI implements Message<MessageOpenGUI> {

    private UUID uuid;

    public MessageOpenGUI(){
    }

    public MessageOpenGUI(AbstractInventoryVehicleEntity inventoryEntity){
        this.uuid = inventoryEntity.getUUID();
    }

    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    public void executeServerSide(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        player.getCommandSenderWorld().getEntitiesOfClass(AbstractInventoryVehicleEntity.class, player.getBoundingBoxForCulling()
                        .inflate(16.0D), v -> v
                        .getUUID()
                        .equals(this.uuid))
                .stream()
                .filter(Entity::isAlive)
                .findAny()
                .ifPresent(entity -> entity.openGUI(player));
    }

    public MessageOpenGUI fromBytes(FriendlyByteBuf buf) {
        this.uuid = buf.readUUID();
        return this;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(uuid);
    }
}
