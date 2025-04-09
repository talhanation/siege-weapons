package com.talhanation.siegeweapons.entities;

import net.minecraft.world.entity.LivingEntity;

public interface IShootingWeapon {

    void shootWeapon();
    void playShootSound();

    void updateTrigger(boolean trigger, LivingEntity livingEntity);
}
