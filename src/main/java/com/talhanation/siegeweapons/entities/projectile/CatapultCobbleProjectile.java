package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CatapultCobbleProjectile extends AbstractCatapultProjectile {

    public static CatapultCobbleProjectile factory(EntityType<? extends CatapultCobbleProjectile> entityType, Level level) {
        return new CatapultCobbleProjectile(entityType, level);
    }

    public float getDamage(){
        return 35F;
    }

    @Override
    public float getAreaDamage() {
        return 1.15F;
    }

    @Override
    public boolean getFireSpread() {
        return false;
    }

    @Override
    public boolean getExplode() {
        return false;
    }

    public CatapultCobbleProjectile(EntityType<? extends CatapultCobbleProjectile> type, Level world) {
        super(type, world);
    }
    public CatapultCobbleProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.CATAPULT_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

}
