package com.talhanation.siegeweapons.entities;

import com.talhanation.siegeweapons.entities.projectile.*;
import com.talhanation.siegeweapons.math.Kalkuel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BallistaEntity extends AbstractInventoryVehicleEntity implements IShootingWeapon{

    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(BallistaEntity.class, EntityDataSerializers.INT);
    private float loaderRotation;
    private LivingEntity driver;
    private int loadingTime;
    public BallistaEntity(EntityType<? extends AbstractInventoryVehicleEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("state", this.getState().getIndex());

    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setState(BallistaEntity.BallistaState.fromIndex(compoundTag.getInt("state")));

    }

    @Override
    public void tick() {
        super.tick();

        BallistaState state = getState();

        if(state == BallistaState.LOADING && ++loadingTime >= 100){
            loadingTime = 0;
            setState(BallistaState.LOADED);
        }

        updateDriverTurnControl();
        updateLoaderRotation();
    }


    public void updateDriverTurnControl() {
        if(getControllingPassenger() != null){
            LivingEntity driver = getControllingPassenger();

            float xRot = getControllingPassenger().getXRot();
            xRot = Mth.clamp(xRot, -90, 20);

            float yRot = driver.getYRot();

            this.setXRot(xRot);
            this.setYRot(yRot);
            driver.setYRot(yRot);

            setDeltaMovement(Kalkuel.calculateMotionX(this.getSpeed(), this.getYRot()), getDeltaMovement().y, Kalkuel.calculateMotionZ(this.getSpeed(), this.getYRot()));
        }

    }

    public void setState(BallistaState state) {
        if(getControllingPassenger() != null) {
            this.driver = getControllingPassenger();
        }
        this.entityData.set(STATE, state.getIndex());
    }

    public BallistaState getState(){
        return BallistaState.fromIndex(entityData.get(STATE));
    }

    protected boolean tryRiding(Entity entity) {
        if(super.tryRiding(entity) && entity instanceof LivingEntity livingEntity){
            this.driver = livingEntity;
            return true;
        }
        return false;
    }

    public void updateLoaderRotation() {
        loaderRotation += getLoaderRotationAmount();
    }

    public float getLoaderRotation(float partialTicks) {
        return loaderRotation + getLoaderRotationAmount() * partialTicks;
    }

    public float getLoaderRotationAmount() {
        return this.getState() == BallistaState.LOADING ? 0.13F : 0F;
    }

    @Override
    public void openGUI(Player player) {

    }

    @Override
    public int getMaxPassengerSize() {
        return 1;
    }

    @Override
    public int getMaxSpeedInKmH() {
        return 3;
    }

    @Override
    public float getMaxHealth() {
        return 200;
    }

    @Override
    public boolean itemInteraction(Player player, InteractionHand interactionHand) {
        //TODO: add load mechanic
        if(getState() == BallistaState.UNLOADED){
            setState(BallistaState.LOADING);
            return true;
        }

        if(getState() == BallistaState.LOADED){
            setState(BallistaState.PROJECTILE_LOADED);
            return true;
        }

        if(getState() == BallistaState.PROJECTILE_LOADED){
            shootWeapon();
            setState(BallistaState.UNLOADED);
            return true;
        }

        return false;
    }

    @Override
    public Vec3 getDriverPosition() {
        double f = -1.8F;
        double d = 0.0F;
        return (new Vec3(f, 0.0D, 0.0D + d)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
    }

    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(entity)) {
            Vec3 vec = getDriverPosition();

            moveFunction.accept(entity, this.getX() + vec.x, this.getY(), this.getZ() + vec.z);
        }
    }
    float speed = 4.0F;
    @Override
    public void shootWeapon() {
        Vec3 forward = this.getForward();
        double yShootVec = forward.y();
        this.shoot(forward, yShootVec, this.getControllingPassenger(), speed);
    }

    /*
     * Method Important for reflection
     */

    double shootHight = 1.3D;
    public void shoot(Vec3 shootVec, double yShootVec, LivingEntity driverEntity, float speed){
        if(driverEntity == null){
            if(driver == null) return;
            driverEntity = driver;
        }

        BallistaProjectile projectile = new BallistaProjectile(this.getCommandSenderWorld(), driverEntity, this.getX(), this.getY() + shootHight, this.getZ());

        projectile.shoot(shootVec.x(), yShootVec, shootVec.z(), speed, projectile.getAccuracy());
        this.getCommandSenderWorld().addFreshEntity(projectile);
        this.playShootSound();



        //this.consumeProjectile();
    }

    @Override
    public void playShootSound() {
        this.playSound(SoundEvents.CROSSBOW_SHOOT, 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public void tigger() {
        if(getState() == BallistaState.UNLOADED){
            setState(BallistaState.LOADING);
            return;
        }

        if(getState() == BallistaState.LOADED){
            setState(BallistaState.PROJECTILE_LOADED);
            return;
        }

        if(getState() == BallistaState.PROJECTILE_LOADED){
            shootWeapon();
            setState(BallistaState.UNLOADED);
        }
    }

    public enum BallistaState {
        UNLOADED(0),
        LOADING(1),
        LOADED(2),
        PROJECTILE_LOADED(3);

        private final int index;

        BallistaState(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static BallistaState fromIndex(int x){
            for (BallistaState state : BallistaState.values()) {
                if (state.getIndex() == x) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Invalid Status index: " + x);
        }
    }
}
