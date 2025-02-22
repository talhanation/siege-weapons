package com.talhanation.siegeweapons.inventory;

import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import com.talhanation.siegeweapons.init.ModMenus;
import de.maxhenkel.corelib.inventory.ContainerBase;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;

public class SiegeTableMenu extends ContainerBase {
    //private final Container vehicleInventory;
    private final SiegeTableBlockEntity entity;

    public SiegeTableMenu(int id, SiegeTableBlockEntity entity, Inventory playerInventory) {
        super(ModMenus.SIEGE_TABLE_CONTAINER.get(), id, playerInventory, new SimpleContainer(0));
        this.entity = entity;
        //this.vehicleInventory = entity.getInventory();

        addPlayerInventorySlots();
        //addVehicleInventorySlots();
    }

    public SiegeTableBlockEntity getEntity() {
        return entity;
    }

    @Override
    public int getInvOffset() {
        return 56;
    }

}
