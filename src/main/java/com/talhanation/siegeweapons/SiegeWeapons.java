package com.talhanation.siegeweapons;

import com.talhanation.siegeweapons.entities.BallistaEntity;
import com.talhanation.siegeweapons.entities.CatapultEntity;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import com.talhanation.siegeweapons.init.ModItems;
import com.talhanation.siegeweapons.world.recipe.SiegeTableRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Function;

public enum SiegeWeapons {
    CATAPULT(0, (level) -> new CatapultEntity(ModEntityTypes.CATAPULT.get(), level), SiegeTableRecipe.CATAPULT_RECIPE, 1.25F, 4, 0, 0),
    BALLISTA(1, (level) -> new BallistaEntity(ModEntityTypes.BALLISTA.get(), level), SiegeTableRecipe.BALLISTA_RECIPE, 3.25F, 0, 0, 0),
    BALLISTA_BOLT(2, (level) -> new ItemEntity(level, 0, 0, 0, ModItems.BALLISTA_PROJECTILE_ITEM.get().getDefaultInstance()), SiegeTableRecipe.BALLISTA_BOLT, 12F, 4, -5, 10),
    COBBLE_CLUSTER(3, (level) -> new ItemEntity(level, 0, 0, 0, ModItems.COBBLE_CLUSTER_ITEM.get().getDefaultInstance()), SiegeTableRecipe.COBBLE_CLUSTER, 15F, 5, 20, 0),
    FIRE_POT(4, (level) -> new ItemEntity(level, 0, 0, 0, ModItems.FIRE_POT_ITEM.get().getDefaultInstance()), SiegeTableRecipe.FIRE_POT, 15F, 5, 5, 0),
    EXPLOSION_POT(5, (level) -> new ItemEntity(level, 0, 0, 0, ModItems.EXPLOSION_POT_ITEM.get().getDefaultInstance()), SiegeTableRecipe.EXPLOSION_POT, 15F, 5, 5, 0);

    private static final SiegeWeapons[] VALUES = values();
    private final Function<Level, Entity> entityFactory;
    private final int index;
    private float renderScale;
    private int renderXOffset;
    private int renderYOffset;
    private int renderZOffset;
    private Entity cachedEntity;
    private final SiegeTableRecipe siegeTableRecipe;
    SiegeWeapons(int index, Function<Level, Entity> entityFactory, SiegeTableRecipe siegeTableRecipe, float renderScale, int renderXOffset, int renderYOffset, int renderZOffset) {
        this.index = index;
        this.entityFactory = entityFactory;
        this.siegeTableRecipe = siegeTableRecipe;
        this.renderScale = renderScale;
        this.renderXOffset = renderXOffset;
        this.renderYOffset = renderYOffset;
        this.renderZOffset = renderZOffset;
    }
    @OnlyIn(Dist.CLIENT)
    public Entity getEntityInClient() {
        if (cachedEntity == null) {
            cachedEntity = entityFactory.apply(Minecraft.getInstance().level);
        }
        return cachedEntity;
    }
    public Entity getEntity(Level level) {
        return entityFactory.apply(level);
    }

    public int getIndex() {
        return index;
    }

    public float getRenderScale() {
        return renderScale;
    }
    public int renderYOffset() {
        return renderYOffset;
    }

    public int renderXOffset() {
        return renderXOffset;
    }
    public int renderZOffset() {
        return renderZOffset;
    }

    public SiegeTableRecipe getRecipe() {
        return siegeTableRecipe;
    }

    public SiegeWeapons getNext() {
        return VALUES[(this.ordinal() + 1) % VALUES.length];
    }

    public SiegeWeapons getBefore() {
        return VALUES[(this.ordinal() - 1 + VALUES.length) % VALUES.length];
    }
    @Nullable
    public static SiegeWeapons fromIndex(int x){
        for (SiegeWeapons weapon : SiegeWeapons.values()) {
            if (weapon.getIndex() == x) {
                return weapon;
            }
        }
        return null;
    }
}
