package com.talhanation.siegeweapons.items;

import com.talhanation.siegeweapons.client.render.item.ExplosionPotItemRenderer;
import com.talhanation.siegeweapons.entities.projectile.ExplosionPotProjectile;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ExplosionPotItem extends Item {
    private static final Predicate<Entity> X = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith);

    public ExplosionPotItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ExplosionPotItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
            }
        });
    }

    public ExplosionPotProjectile getEntity(Level world) {
        return new ExplosionPotProjectile(ModEntityTypes.EXPLOSION_POT_PROJECTILE.get(), world);
    }
}
