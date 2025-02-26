package com.talhanation.siegeweapons.entities;

import com.talhanation.siegeweapons.client.render.CatapultRenderer;
import com.talhanation.siegeweapons.entities.projectile.CatapultProjectile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
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
        compoundTag.putFloat("range", this.getRange());
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setRange(compoundTag.getInt("range"));
    }

    public void setRange(float x) {
        if(x > 100) x = 100;
        if(x < 0) x = 0;

        entityData.set(RANGE, x);
    }

    public float getRange(){
        return entityData.get(RANGE);
    }


    @Override
    public boolean itemInteraction(Player player, InteractionHand interactionHand) {
        //TODO: add load mechanic

        return false;
    }

    @Override
    public Vec3 getDriverPosition() {
        double f = -2.5F;
        double d = -1.0F;
        return (new Vec3(f, 0.0D, 0.0D + d)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
    }

    @Override
    public int getMaxPassengerSize() {
        return 1;
    }

    @Override
    public int getMaxSpeedInKmH() {
        return 7;//TODO: CONFIG
    }

    @Override
    public float getMaxHealth() {
        return 300;//TODO: CONFIG
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

    @Override
    public void shootWeapon() {
        Vec3 forward = this.getForward();
        float range = getRange();
        double speed = 3.0F + range * 0.1F;
        double accuracy = 8F;// 0 = 100%
        double yShootVec = forward.y() + 45F/40F;

        this.shoot(forward, yShootVec, this.getControllingPassenger(), speed, accuracy);
    }

    /*
     * Method Important for reflection
     */
    public void shoot(Vec3 shootVec, double yShootVec, LivingEntity driverEntity, double speed, double accuracy){
        //TODO: getter for projectile type

        CatapultProjectile projectile = new CatapultProjectile(this.getCommandSenderWorld(), driverEntity, this.getX(), this.getY() + 1, this.getZ());
        projectile.shoot(shootVec.x(), yShootVec, shootVec.z(), (float) speed, (float) accuracy);
        this.getCommandSenderWorld().addFreshEntity(projectile);

        this.playShootSound();
        //this.consumeProjectile();

    }

    @Override
    public void playShootSound() {
        //TODO: add real catapult sound
        this.playSound(SoundEvents.TRIDENT_THROW, 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
    }
}
