package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    /*
    public static final RegistryObject<Block> RECRUIT_BLOCK = BLOCKS.register("recruit_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.FLETCHING_TABLE)));
    */

}
