package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.entities.CatapultEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;


public class MessageSetCatapultRange implements Message<MessageSetCatapultRange> {
    int amount;
    public MessageSetCatapultRange() {
    }

    public MessageSetCatapultRange(int amount) {
        this.amount = amount;
    }

    @Override
    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        Entity entity = context.getSender().getVehicle();

        if(entity instanceof CatapultEntity catapult){
            catapult.setRange(catapult.getRange() + amount);

        }
    }

    @Override
    public MessageSetCatapultRange fromBytes(FriendlyByteBuf buf) {
        this.amount = buf.readInt();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.amount);
    }

}
