package com.talhanation.siegeweapons.items;

import com.talhanation.siegeweapons.client.render.item.BallistaProjectileItemRenderer;
import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.entities.projectile.BallistaProjectile;
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

public class BallistaProjectileItem extends Item {
    private static final Predicate<Entity> X = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith);

    public BallistaProjectileItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new BallistaProjectileItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
            }
        });
    }

    public BallistaProjectile getEntity(Level world) {
        return new BallistaProjectile(ModEntityTypes.BALLISTA_PROJECTILE.get(), world);
    }
}
