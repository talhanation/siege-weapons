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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SiegeTableBlock extends BaseEntityBlock {
    public SiegeTableBlock(BlockBehaviour.Properties properties) {
        super(properties);

    }

    public @NotNull RenderShape getRenderShape(@NotNull BlockState p_50950_) {
        return RenderShape.MODEL;
    }

    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SiegeTableBlockEntity(blockPos, blockState);
    }

    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof SiegeTableBlockEntity siegeTableBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, siegeTableBlockEntity, blockPos);
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

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        //TODO: Particles
    }
}
