package com.talhanation.siegeweapons.items;

import com.talhanation.siegeweapons.client.render.item.BallistaProjectileItemRenderer;
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleBundleProjectile;
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

public class CobbleBundleItem extends Item {
    private static final Predicate<Entity> X = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith);

    public CobbleBundleItem(Properties properties) {
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

    public CatapultCobbleBundleProjectile getEntity(Level world) {
        return new CatapultCobbleBundleProjectile(ModEntityTypes.COBBLE_BUNDLE_PROJECTILE.get(), world);
    }
}
