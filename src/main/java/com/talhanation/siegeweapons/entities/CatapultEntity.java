package com.talhanation.siegeweapons.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CatapultEntity extends AbstractInventoryVehicleEntity implements IShootingWeapon {

    private static final EntityDataAccessor<Float> RANGE = SynchedEntityData.defineId(CatapultEntity.class, EntityDataSerializers.FLOAT);

    public CatapultEntity(EntityType<? extends AbstractInventoryVehicleEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RANGE, 50F);
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putFloat("range", this.getHealth());
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);

    }

    public void setHealth(float x) {
        entityData.set(RANGE, x);
    }

    public float getHealth(){
        return entityData.get(RANGE);
    }

    @Override
    public boolean itemInteraction(Player player, InteractionHand interactionHand) {
        //TODO: add load mechanic

        return false;
    }

    @Override
    public Vec3 getDriverPosition() {
        double f = -2F;
        double d = -0.75F;
        return (new Vec3(f, 0.0D, 0.0D + d)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
    }

    @Override
    public int getMaxPassengerSize() {
        return 1;
    }

    @Override
    public int getMaxSpeedInKmH() {
        return 7;
    }

    @Override
    public float getMaxHealth() {
        return 300;
    }

    @Override
    public void openGUI(Player player) {

    }
    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(entity)) {
            Vec3 vec = getDriverPosition();

            moveFunction.accept(entity, this.getX() + vec.x, this.getY(), this.getZ() + vec.z);
        }
    }

    public void shoot(){

    }

}
