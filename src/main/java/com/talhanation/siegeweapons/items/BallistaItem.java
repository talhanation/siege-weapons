package com.talhanation.siegeweapons.items;

import com.talhanation.siegeweapons.client.render.item.BallistaItemRenderer;
import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class BallistaItem extends Item {
    private static final Predicate<Entity> X = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith);

    public BallistaItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new BallistaItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
            }
        });
    }

    public BallistaEntity getEntity(Level world) {
        BallistaEntity entity = new BallistaEntity(ModEntityTypes.BALLISTA.get(), world);
        entity.setState(BallistaEntity.BallistaState.LOADED);
        return entity;
    }
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            if (hitresult.getType() == HitResult.Type.BLOCK) {

                BallistaEntity entity = new BallistaEntity(ModEntityTypes.CATAPULT.get(), level);
                entity.setState(BallistaEntity.BallistaState.LOADED);
                entity.setYRot(player.getYRot() + 90F);
                entity.setPos(hitresult.getLocation().add(0,1,0));

                if (!level.isClientSide) {
                    level.addFreshEntity(entity);
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, hitresult.getLocation());
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

}
