package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CatapultFirePotProjectile extends AbstractCatapultProjectile {

    public static CatapultFirePotProjectile factory(EntityType<? extends CatapultFirePotProjectile> entityType, Level level) {
        return new CatapultFirePotProjectile(entityType, level);
    }

    public float getDamage(){
        return 15F;
    }

    @Override
    public float getAreaDamage() {
        return 0F;
    }

    @Override
    public float getAreaDamageChance() {
        return 20;
    }

    @Override
    public boolean getFireSpread() {
        return true;
    }

    @Override
    public boolean getExplode() {
        return true;
    }

    @Override
    public float getAccuracy() {
        return 1; //0 = 100%
    }

    public CatapultFirePotProjectile(EntityType<? extends CatapultFirePotProjectile> type, Level world) {
        super(type, world);
    }
    public CatapultFirePotProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.CATAPULT_FIRE_POT_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

}
