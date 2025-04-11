package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CatapultCobbleBundleProjectile extends AbstractCatapultProjectile {

    public static CatapultCobbleBundleProjectile factory(EntityType<? extends CatapultCobbleBundleProjectile> entityType, Level level) {
        return new CatapultCobbleBundleProjectile(entityType, level);
    }

    public float getDamage(){
        return 20F;
    }

    @Override
    public float getAreaDamage() {
        return 0.85F;
    }
    @Override
    public boolean getFireSpread() {
        return false;
    }

    @Override
    public boolean getExplode() {
        return false;
    }

    @Override
    public float getAccuracy() {
        return 7;
    }

    public CatapultCobbleBundleProjectile(EntityType<? extends CatapultCobbleBundleProjectile> type, Level world) {
        super(type, world);
    }
    public CatapultCobbleBundleProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.COBBLE_BUNDLE_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

}
