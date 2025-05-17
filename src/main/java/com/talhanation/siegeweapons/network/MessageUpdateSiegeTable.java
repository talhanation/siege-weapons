package com.talhanation.siegeweapons.network;

import com.talhanation.siegeweapons.SiegeWeapons;
import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import de.maxhenkel.corelib.net.Message;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;


public class MessageUpdateSiegeTable implements Message<MessageUpdateSiegeTable> {

    private BlockPos pos;
    private int selectionId;
    private boolean startCrafting;
    private UUID playerUUID;
    public MessageUpdateSiegeTable() {
    }

    public MessageUpdateSiegeTable(BlockPos pos, int selectionId, boolean startCrafting, UUID playerUUID) {
        this.pos = pos;
        this.selectionId = selectionId;
        this.startCrafting = startCrafting;
        this.playerUUID = playerUUID;
    }

    @Override
    public Dist getExecutingSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void executeServerSide(NetworkEvent.Context context) {
        BlockEntity entity = context.getSender().getCommandSenderWorld().getBlockEntity(this.pos);
        ServerPlayer serverPlayer = entity.getLevel().getEntitiesOfClass(ServerPlayer.class, new AABB(entity.getBlockPos()).inflate(16)).stream().filter(player -> player.getUUID().equals(playerUUID)).findAny().get();

        if(serverPlayer == null) return;

        if (!(entity instanceof SiegeTableBlockEntity siegeTableBlockEntity)) {
            return;
        }
        siegeTableBlockEntity.selection = SiegeWeapons.fromIndex(this.selectionId);
        if(siegeTableBlockEntity.selection == null) return;

        if(startCrafting){
            siegeTableBlockEntity.selection.getRecipe().consumeMaterials(serverPlayer.getInventory());
            siegeTableBlockEntity.startCrafting(selectionId);
        }
    }

    @Override
    public MessageUpdateSiegeTable fromBytes(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.selectionId = buf.readInt();
        this.startCrafting = buf.readBoolean();
        this.playerUUID = buf.readUUID();
        return this;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);
        buf.writeInt(this.selectionId);
        buf.writeBoolean(this.startCrafting);
        buf.writeUUID(this.playerUUID);
    }

}
