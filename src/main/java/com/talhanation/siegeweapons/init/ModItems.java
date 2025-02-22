package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<BlockItem> SIEGE_TABLE_BLOCK = ITEMS.register("siege_table_block", () -> new BlockItem(ModBlocks.SIEGE_TABLE_BLOCK.get(), new Item.Properties()));


}