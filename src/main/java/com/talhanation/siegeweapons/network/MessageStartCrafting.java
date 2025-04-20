package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;


public class MessageStartCrafting implements Message<MessageStartCrafting> {

    private BlockPos pos;
    private int craftID;
    public MessageStartCrafting() {
    }

    public MessageStartCrafting(BlockPos pos, int craftID) {
        this.pos = pos;
        this.craftID = craftID;
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

        siegeTableBlockEntity.startCrafting(craftID);
    }

    @Override
    public MessageStartCrafting fromBytes(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.craftID = buf.readInt();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);
        buf.writeInt(this.craftID);

    }

}
