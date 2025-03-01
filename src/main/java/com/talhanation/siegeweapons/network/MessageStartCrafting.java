package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;


public class MessageStartCrafting implements Message<MessageStartCrafting> {

    private BlockPos pos;
    public MessageStartCrafting() {
    }

    public MessageStartCrafting(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        BlockEntity entity = context.getSender().getCommandSenderWorld().getBlockEntity(this.pos);

        if (!(entity instanceof SiegeTableBlockEntity siegeTableBlockEntity)) {
            return;
        }

        siegeTableBlockEntity.startCrafting();
    }

    @Override
    public MessageStartCrafting fromBytes(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);

    }

}
