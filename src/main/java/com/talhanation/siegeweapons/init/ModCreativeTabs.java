package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SIEGE_TAB = CREATIVE_MODE_TABS.register("siegeweapons_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.siegeweapons.siegeweapons_tab"))
                    .icon(() -> new ItemStack(ModItems.CATAPULT_ITEM.get()))
                    .displayItems((params, output) -> {
                        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                            output.accept(item.get());
                        }
                    })
                    .build()
    );
}

