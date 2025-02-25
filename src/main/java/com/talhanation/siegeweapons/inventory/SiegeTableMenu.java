package com.talhanation.siegeweapons.inventory;

import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import com.talhanation.siegeweapons.client.gui.SiegeTableScreen;
import com.talhanation.siegeweapons.init.ModMenus;
import de.maxhenkel.corelib.inventory.ContainerBase;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

public class SiegeTableMenu extends ContainerBase {

    private final SiegeTableBlockEntity entity;

    @Nullable
    private SiegeTableScreen screen;
    public SiegeTableMenu(int id, SiegeTableBlockEntity entity, Inventory playerInventory) {
        super(ModMenus.SIEGE_TABLE_CONTAINER.get(), id, playerInventory, new SimpleContainer(0));
        this.entity = entity;
        this.addPlayerInventorySlots();
    }

    public void setScreen(@Nullable SiegeTableScreen screen) {
        this.screen = screen;
    }

    public SiegeTableBlockEntity getEntity() {
        return entity;
    }

    @Override
    public int getInvOffset() {
        return 0;
    }

    @Override
    protected void addPlayerInventorySlots() {
        if (this.playerInventory != null) {
            int k;
            for (k = 0; k < 3; ++k) {
                for (int j = 0; j < 9; ++j) {
                    this.addSlot(new Slot(this.playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18 + this.getInvOffset()) {
                        @Override
                        public void setChanged() {
                            super.setChanged();
                            if (screen != null) {
                                screen.onPlayerInventoryChanged();
                            }
                        }
                    });
                }
            }

            for (k = 0; k < 9; ++k) {
                this.addSlot(new Slot(this.playerInventory, k, 8 + k * 18, 142 + this.getInvOffset()) {
                    @Override
                    public void setChanged() {
                        super.setChanged();
                        if (screen != null) {
                            screen.onPlayerInventoryChanged();
                        }
                    }
                });
            }
        }
    }

}
