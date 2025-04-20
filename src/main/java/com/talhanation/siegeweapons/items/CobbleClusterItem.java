package com.talhanation.siegeweapons.items;

import com.talhanation.siegeweapons.client.render.item.CobbleClusterItemRenderer;
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleClusterProjectile;
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

public class CobbleClusterItem extends Item {
    private static final Predicate<Entity> X = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith);

    public CobbleClusterItem(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new CobbleClusterItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
            }
        });
    }

    public CatapultCobbleClusterProjectile getEntity(Level world) {
        return new CatapultCobbleClusterProjectile(ModEntityTypes.COBBLE_CLUSTER_PROJECTILE.get(), world);
    }
}
