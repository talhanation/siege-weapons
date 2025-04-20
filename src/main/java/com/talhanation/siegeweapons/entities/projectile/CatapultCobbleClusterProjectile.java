package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CatapultCobbleClusterProjectile extends AbstractCatapultProjectile {

    public static CatapultCobbleClusterProjectile factory(EntityType<? extends CatapultCobbleClusterProjectile> entityType, Level level) {
        return new CatapultCobbleClusterProjectile(entityType, level);
    }

    public float getDamage(){
        return 20F;
    }

    @Override
    public float getAreaDamage() {
        return 1.0F;
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

    public CatapultCobbleClusterProjectile(EntityType<? extends CatapultCobbleClusterProjectile> type, Level world) {
        super(type, world);
    }
    public CatapultCobbleClusterProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.COBBLE_CLUSTER_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

}
