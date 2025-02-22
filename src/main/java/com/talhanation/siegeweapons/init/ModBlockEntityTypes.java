package com.talhanation.siegeweapons.init;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.blocks.SiegeTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {


    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Main.MOD_ID);

    public static final RegistryObject<BlockEntityType<SiegeTableBlockEntity>> SIEGE_TABLE_BLOCK_ENTITY = BLOCK_ENTITIES.register("siege_table_block_entity",
            () -> BlockEntityType.Builder.of(SiegeTableBlockEntity::new, ModBlocks.SIEGE_TABLE_BLOCK.get())
                    .build(null)
    );



}
