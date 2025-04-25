package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.entities.BallistaEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;


public class MessageTryLoadFromHand implements Message<MessageTryLoadFromHand> {

    public MessageTryLoadFromHand() {
    }

    @Override
    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if(player == null) return;

        Entity entity = context.getSender().getVehicle();

        if(entity instanceof BallistaEntity ballista){
            ballista.tryLoadFromHand(player);
        }
    }

    @Override
    public MessageTryLoadFromHand fromBytes(FriendlyByteBuf buf) {
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

}
