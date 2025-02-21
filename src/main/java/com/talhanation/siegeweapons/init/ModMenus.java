package com.talhanation.siegeweapons.init;


import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.gui.InventoryVehicleScreen;
import com.talhanation.siegeweapons.entities.AbstractInventoryVehicleEntity;
import com.talhanation.siegeweapons.inventory.VehicleInventoryMenu;
import de.maxhenkel.corelib.inventory.ContainerBase;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.UUID;


public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Main.MOD_ID);

    public static void registerMenus() {
        registerMenu(VEHICLE_INVENTORY.get(), InventoryVehicleScreen::new);
    }

    public static final RegistryObject<MenuType<VehicleInventoryMenu>> VEHICLE_INVENTORY =
        MENU_TYPES.register("vehicle_container", () -> IForgeMenuType.create((windowId, inv, data) -> {
            try {
                UUID uuid = data.readUUID();
                AbstractInventoryVehicleEntity vehicle = getInventoryVehicleByUUID(inv.player, uuid);

                if (vehicle == null) {
                    return null;
                }
                return new VehicleInventoryMenu(windowId, vehicle, inv);

            } catch (Exception e) {
                return null;
            }
    }));

    public static AbstractInventoryVehicleEntity getInventoryVehicleByUUID(Player player, UUID uuid) {
        double distance = 16D;
        return player.getCommandSenderWorld().getEntitiesOfClass(
                AbstractInventoryVehicleEntity.class,
                    new AABB(
                            player.getX() - distance,
                            player.getY() - distance,
                            player.getZ() - distance,
                            player.getX() + distance,
                            player.getY() + distance,
                            player.getZ() + distance),
                    entity -> entity.getUUID().equals(uuid)
            ).stream().findAny().orElse(null);
    }

    /**
     * Registers a menuType/container with a screen constructor.
     *
     * It has a try/catch block because the Forge screen constructor fails silently.
     * Do not remove "redundant" cast
     */
    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void registerMenu(MenuType<? extends M> menuType, MenuScreens.ScreenConstructor<M, U> screenConstructor) {
        MenuScreens.register(menuType, (MenuScreens.ScreenConstructor<M, U>) (menu, inventory, title) -> {
            try {
                return screenConstructor.create(menu, inventory, title);
            } catch (Exception e) {
                return null;
            }
        });
    }
}
