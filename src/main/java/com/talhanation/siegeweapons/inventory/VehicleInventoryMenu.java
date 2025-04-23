package com.talhanation.siegeweapons.inventory;

import com.talhanation.siegeweapons.entities.AbstractInventoryVehicleEntity;
import com.talhanation.siegeweapons.init.ModMenus;
import de.maxhenkel.corelib.inventory.ContainerBase;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class VehicleInventoryMenu extends ContainerBase {
    private final Container vehicleInventory;
    private final AbstractInventoryVehicleEntity inventoryEntity;

    public VehicleInventoryMenu(int id, AbstractInventoryVehicleEntity inventoryEntity, Inventory playerInventory) {
        super(ModMenus.VEHICLE_INVENTORY.get(), id, playerInventory, inventoryEntity.getInventory());
        this.inventoryEntity = inventoryEntity;
        this.vehicleInventory = inventoryEntity.getInventory();

        addPlayerInventorySlots();
        //addVehicleInventorySlots();
    }

    public AbstractInventoryVehicleEntity getEntity() {
        return inventoryEntity;
    }

    @Override
    public int getInvOffset() {
        return 0;
    }

    public void addVehicleInventorySlots() {
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                this.addSlot(new Slot(vehicleInventory, 6 + l + k * inventoryEntity.getInventoryColumns(), 2 * 18 + 82 + l * 18,  18 + k * 18));
            }
        }
    }
}
