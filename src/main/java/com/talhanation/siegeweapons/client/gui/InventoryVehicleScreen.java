package com.talhanation.siegeweapons.client.gui;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.inventory.VehicleInventoryMenu;
import de.maxhenkel.corelib.inventory.ScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InventoryVehicleScreen extends ScreenBase<VehicleInventoryMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID,"textures/gui/siege_container.png" );

    public InventoryVehicleScreen(VehicleInventoryMenu container, Inventory playerInventory, Component title) {
        super(TEXTURE, container, playerInventory, title);
    }
}
