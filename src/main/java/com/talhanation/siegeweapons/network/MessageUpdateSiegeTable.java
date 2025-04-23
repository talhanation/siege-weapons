package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.SiegeWeapons;
import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;


public class MessageUpdateSiegeTable implements Message<MessageUpdateSiegeTable> {

    private BlockPos pos;
    private int selectionId;
    private boolean startCrafting;
    public MessageUpdateSiegeTable() {
    }

    public MessageUpdateSiegeTable(BlockPos pos, int selectionId, boolean startCrafting) {
        this.pos = pos;
        this.selectionId = selectionId;
        this.startCrafting = startCrafting;
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
        siegeTableBlockEntity.selection = SiegeWeapons.fromIndex(this.selectionId);

        if(startCrafting){
            siegeTableBlockEntity.startCrafting(selectionId);
        }
    }

    @Override
    public MessageUpdateSiegeTable fromBytes(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.selectionId = buf.readInt();
        this.startCrafting = buf.readBoolean();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);
        buf.writeInt(this.selectionId);
        buf.writeBoolean(this.startCrafting);
    }

}
