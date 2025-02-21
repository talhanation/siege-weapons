package com.talhanation.siegeweapons.entities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;


public abstract class AbstractInventoryVehicleEntity extends AbstractVehicleEntity {
    public SimpleContainer inventory;
    private net.minecraftforge.common.util.LazyOptional<?> itemHandler = null;

    public AbstractInventoryVehicleEntity(EntityType<? extends AbstractInventoryVehicleEntity> entityType, Level world) {
        super(entityType, world);
        this.createInventory();
    }

    ///////////////////////////////////TICK/////////////////////////////////////////

    public void tick() {
        super.tick();
    }

    ////////////////////////////////////DATA////////////////////////////////////

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        ListTag listnbt = new ListTag();
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                CompoundTag compoundnbt = new CompoundTag();
                compoundnbt.putByte("Slot", (byte) i);
                itemstack.save(compoundnbt);
                listnbt.add(compoundnbt);
            }
        }

        nbt.put("Items", listnbt);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        ListTag listnbt = nbt.getList("Items", 10);//muss 10 sein amk sonst nix save
        this.createInventory();

        for (int i = 0; i < listnbt.size(); ++i) {
            CompoundTag compoundnbt = listnbt.getCompound(i);
            int j = compoundnbt.getByte("Slot") & 255;
            if (j < this.inventory.getContainerSize()) {
                this.inventory.setItem(j, ItemStack.of(compoundnbt));
            }
        }
    }


    ////////////////////////////////////GET////////////////////////////////////

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public int getInventorySize() {
        return 9;
    }

    public int getInventoryColumns() {
        return 3;
    }

    ////////////////////////////////////OTHER FUNCTIONS////////////////////////////////////

    public void createInventory() {
        SimpleContainer inventory = this.inventory;
        this.inventory = new SimpleContainer(getInventorySize()){

        };
        if (inventory != null) {
            int i = Math.min(inventory.getContainerSize(), this.inventory.getContainerSize());

            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = inventory.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }
        this.itemHandler = net.minecraftforge.common.util.LazyOptional.of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this.inventory));
    }

    public void kill() {
        super.kill();

        if(this.getCommandSenderWorld().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS))
            for (int i = 0; i < this.inventory.getContainerSize(); i++)
                 this.spawnAtLocation(this.inventory.getItem(i));// Containers.dropItemStack(this.getCommandSenderWorld(), getX(), getY(), getZ(), );
    }

    /*
    public boolean hasSameTypeOfItem(ItemStack stack) {
        return this.getInventory().items.stream().anyMatch(itemStack -> itemStack.getDescriptionId().equals(stack.getDescriptionId()));
    }
    */

    public abstract void openGUI(Player player);

    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, Direction facing) {
        if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && itemHandler != null)
            return itemHandler.cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if (itemHandler != null) {
            net.minecraftforge.common.util.LazyOptional<?> oldHandler = itemHandler;
            itemHandler = null;
            oldHandler.invalidate();
        }
    }
    /*
    public void consumeItemStack(ItemStack itemStackIn){
        for(ItemStack itemStack : this.inventory.items){
            if(itemStack.is(itemStackIn.getItem())){
                itemStack.shrink(1);
                break;
            }
        }
    }
        */
}