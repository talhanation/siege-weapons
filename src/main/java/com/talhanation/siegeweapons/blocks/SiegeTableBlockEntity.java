package com.talhanation.siegeweapons.blocks;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.init.ModBlockEntityTypes;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import com.talhanation.siegeweapons.inventory.SiegeTableMenu;
import com.talhanation.siegeweapons.network.MessageToClientUpdateSiegeTableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

//
public class SiegeTableBlockEntity extends BaseContainerBlockEntity {
    public static final int CRAFTING_TIME = 100; //20 * 60 * 3; // Example value in ticks
    private int craftingTimer = 1;
    private boolean isCrafting = false;

    public SiegeTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.SIEGE_TABLE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.siegetable");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new SiegeTableMenu(i, this, inventory);
    }

    @Override
    public int getContainerSize() {
        return 10; // Example inventory size
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public @NotNull ItemStack getItem(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItem(int index, int count) {
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int index, @NotNull ItemStack stack) {
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public void clearContent() {
    }

    public void startCrafting() {
        if (!isCrafting) {
            isCrafting = true;
        }
    }
    private void cancelCrafting(boolean returnItems) {
        setCrafting(false);
        craftingTimer = 1;
    }
    private void finishCrafting() {
        cancelCrafting(false);
        // Spawn crafted entity near the table
        if (level != null && !level.isClientSide) {
            CatapultEntity catapult = new CatapultEntity(ModEntityTypes.CATAPULT.get(), level);
            catapult.setState(CatapultEntity.CatapultState.LOADED);
            catapult.setPos(this.getBlockPos().above(2).getCenter());

            level.addFreshEntity(catapult);
        }
    }

    public boolean getCrafting(){
        return isCrafting;
    }

    public void setCrafting(boolean x){
        isCrafting = x;
    }

    public int getCraftingTime(){
        return craftingTimer;
    }

    public void setCraftingTime(int x){
       craftingTimer = x;
    }

    public int getCraftingProgress() {
        if (CRAFTING_TIME == 0) return 0;
        return (int) ((craftingTimer / (float) CRAFTING_TIME) * 100);
    }


    public void serverTick() {

        if (isCrafting) {
            craftingTimer++;
            if (craftingTimer >= CRAFTING_TIME) {
                finishCrafting();
            }
        }
        syncToClient();
    }

    public void syncToClient() {
        if (level != null && !level.isClientSide) {
            Main.SIMPLE_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(worldPosition)), new MessageToClientUpdateSiegeTableEntity(this));
        }
    }
}