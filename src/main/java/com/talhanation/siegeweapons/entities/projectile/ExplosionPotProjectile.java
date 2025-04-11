package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class ExplosionPotProjectile extends AbstractCatapultProjectile {

    public static ExplosionPotProjectile factory(EntityType<? extends ExplosionPotProjectile> entityType, Level level) {
        return new ExplosionPotProjectile(entityType, level);
    }

    public float getDamage(){
        return 15F;
    }

    @Override
    public float getAreaDamage() {
        return 3.0F;
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

    public ExplosionPotProjectile(EntityType<? extends ExplosionPotProjectile> type, Level world) {
        super(type, world);
    }
    public ExplosionPotProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.EXPLOSION_POT_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

}
