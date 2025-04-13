package com.talhanation.siegeweapons.entities;

import net.minecraft.world.entity.LivingEntity;

public interface IShootingWeapon {

    void shootWeapon();
    void playShootSound();
    void playLoadingSound();

    void updateTrigger(boolean trigger, LivingEntity livingEntity);

    void setShowTrajectory(boolean rightClickKey);
    boolean getShowTrajectory();
}
