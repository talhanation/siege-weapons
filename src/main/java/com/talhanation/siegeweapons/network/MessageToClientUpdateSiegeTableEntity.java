package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

public class MessageToClientUpdateSiegeTableEntity implements Message<MessageToClientUpdateSiegeTableEntity> {

    private BlockPos pos;
    private int progress;
    private boolean crafting;
    public MessageToClientUpdateSiegeTableEntity() {
    }

    public MessageToClientUpdateSiegeTableEntity(SiegeTableBlockEntity entity) {
        this.pos = entity.getBlockPos();
        this.progress = entity.getCraftingTime();
        this.crafting = entity.getCrafting();
    }

    @Override
    public Dist getExecutingSide() {
        return Dist.CLIENT;
    }

    @Override
    public void executeClientSide(NetworkEvent.Context context) {
        Level world = Minecraft.getInstance().level;
        if (world != null) {
            BlockEntity be = world.getBlockEntity(this.pos);
            if (be instanceof SiegeTableBlockEntity entity) {
                entity.setCraftingTime(this.progress);
                entity.setCrafting(this.crafting);
            }
        }
    }

    @Override
    public MessageToClientUpdateSiegeTableEntity fromBytes(FriendlyByteBuf buf) {
        this.progress = buf.readInt();
        this.pos = buf.readBlockPos();
        this.crafting = buf.readBoolean();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(progress);
        buf.writeBlockPos(pos);
        buf.writeBoolean(crafting);
    }

}
