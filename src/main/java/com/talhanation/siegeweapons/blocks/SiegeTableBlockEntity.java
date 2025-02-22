package com.talhanation.siegeweapons.blocks;

import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.init.ModBlockEntityTypes;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import com.talhanation.siegeweapons.inventory.SiegeTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

//
public class SiegeTableBlockEntity extends BaseContainerBlockEntity {
    private static final int CRAFTING_TIME = 20 * 60 * 3; // Example value in ticks
    private int craftingTimer = 0;
    private boolean isCrafting = false;
    private ItemStack craftingResult = ItemStack.EMPTY;

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

    public void startCrafting(ItemStack result) {
        if (!isCrafting) {
            isCrafting = true;
            craftingTimer = CRAFTING_TIME;
            craftingResult = result;
        }
    }

    public void serverTick() {
        if (isCrafting) {
            craftingTimer--;
            if (craftingTimer <= 0) {
                finishCrafting();
            }
        }
    }
    private void cancelCrafting() {
        isCrafting = false;
        craftingTimer = 0;
    }
    private void finishCrafting() {
        cancelCrafting();
        // Spawn crafted entity near the table
        if (level != null && !level.isClientSide) {
            CatapultEntity catapult = new CatapultEntity(ModEntityTypes.CATAPULT.get(), level);
            catapult.setPos(this.getBlockPos().above(2).getCenter());

            level.addFreshEntity(catapult);
        }
        craftingResult = ItemStack.EMPTY;
    }


    enum SiegeWeapons{
        CART,
        TRANSPORT_CART,
        BALLISTA,
        CATAPULT,
        BATTERING_RAM,


    }
}