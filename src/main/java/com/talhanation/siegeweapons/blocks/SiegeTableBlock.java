package com.talhanation.siegeweapons.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class SiegeTableBlock extends BaseEntityBlock {

    public SiegeTableBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public RenderShape getRenderShape(BlockState p_50950_) {
        return RenderShape.MODEL;
    }

    public BlockEntity newBlockEntity(BlockPos p_152698_, BlockState p_152699_) {
        return new SiegeTableBlockEntity( p_152698_, p_152699_);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof SiegeTableBlockEntity siegeTableBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, siegeTableBlockEntity, blockPos);
                //player.awardStat(Stats.INTERACT_WITH_BREWINGSTAND);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide) {
            return (BlockEntityTicker<T>) (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof SiegeTableBlockEntity siegeTable) {
                    siegeTable.serverTick();
                }
            };
        }
        return null;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity living, ItemStack p_50917_) {
        if (p_50917_.hasCustomHoverName()) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof SiegeTableBlockEntity siegeTableBlockEntity) {
                siegeTableBlockEntity.setCustomName(p_50917_.getHoverName());
            }
        }

    }

    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource source) {
        double d0 = (double)pos.getX() + 0.4D + (double)source.nextFloat() * 0.2D;
        double d1 = (double)pos.getY() + 0.7D + (double)source.nextFloat() * 0.3D;
        double d2 = (double)pos.getZ() + 0.4D + (double)source.nextFloat() * 0.2D;
        level.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState blockState, boolean p_50941_) {
        if (!state.is(blockState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof SiegeTableBlockEntity siegeTableBlockEntity) {
                Containers.dropContents(level, blockPos, siegeTableBlockEntity);
            }

            super.onRemove(state, level, blockPos, blockState, p_50941_);
        }
    }

    public boolean isPathfindable(BlockState p_50921_, BlockGetter p_50922_, BlockPos p_50923_, PathComputationType p_50924_) {
        return false;
    }
}
