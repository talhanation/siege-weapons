package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class FirePotProjectile extends AbstractCatapultProjectile {

    public static FirePotProjectile factory(EntityType<? extends FirePotProjectile> entityType, Level level) {
        return new FirePotProjectile(entityType, level);
    }


    @Override
    public boolean getFireSpread() {
        return true;
    }

    @Override
    public boolean getExplode() {
        return true;
    }

    public FirePotProjectile(EntityType<? extends FirePotProjectile> type, Level world) {
        super(type, world);
        setAreaDamage(1.5F);
        setHurtDamage(15F);
        setAccuracy(1F);
    }
    public FirePotProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.FIRE_POT_PROJECTILE.get(), owner, d1, d2, d3, world);
        setAreaDamage(1.5F);
        setHurtDamage(15F);
        setAccuracy(1F);
    }

}
