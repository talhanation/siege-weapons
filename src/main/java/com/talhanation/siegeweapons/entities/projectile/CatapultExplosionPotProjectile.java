package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CatapultExplosionPotProjectile extends AbstractCatapultProjectile {

    public static CatapultExplosionPotProjectile factory(EntityType<? extends CatapultExplosionPotProjectile> entityType, Level level) {
        return new CatapultExplosionPotProjectile(entityType, level);
    }

    public float getDamage(){
        return 15F;
    }

    @Override
    public float getAreaDamage() {
        return 1.75F;
    }

    @Override
    public boolean getFireSpread() {
        return false;
    }

    @Override
    public boolean getExplode() {
        return true;
    }

    @Override
    public float getAccuracy() {
        return 1; //0 = 100%
    }

    public CatapultExplosionPotProjectile(EntityType<? extends CatapultExplosionPotProjectile> type, Level world) {
        super(type, world);
    }
    public CatapultExplosionPotProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.CATAPULT_EXPLOSION_POT_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

}
