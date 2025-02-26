package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CatapultProjectile extends AbstractCatapultProjectile {

    public static CatapultProjectile factory(EntityType<? extends CatapultProjectile> entityType, Level level) {
        return new CatapultProjectile(entityType, level);
    }

    public CatapultProjectile(EntityType<? extends CatapultProjectile> type, Level world) {
        super(type, world);
    }
    public CatapultProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.CATAPULT_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

}
