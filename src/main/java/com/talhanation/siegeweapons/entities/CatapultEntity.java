package com.talhanation.siegeweapons.entities;

import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleProjectile;
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

public class CatapultEntity extends AbstractInventoryVehicleEntity implements IShootingWeapon {

    private static final EntityDataAccessor<Float> RANGE = SynchedEntityData.defineId(CatapultEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> ANGLE = SynchedEntityData.defineId(CatapultEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(CatapultEntity.class, EntityDataSerializers.INT);
    //private static final EntityDataAccessor<Integer> PROJECTILE = SynchedEntityData.defineId(CatapultEntity.class, EntityDataSerializers.INT);
    private float loaderRotation;
    private float loadingTime;
    private float shootingTime;
    private LivingEntity driver;
    public CatapultEntity(EntityType<? extends AbstractInventoryVehicleEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RANGE, 100F);
        this.entityData.define(ANGLE, -0.65F);
        this.entityData.define(STATE, 4);
    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putFloat("range", this.getRange());
        compoundTag.putInt("state", this.getState().getIndex());
        compoundTag.putFloat("angle", this.getAngle());

    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setRange(compoundTag.getFloat("range"));
        this.setState(CatapultState.fromIndex(compoundTag.getInt("state")));
        this.setAngle(compoundTag.getFloat("angle"));
    }

    @Override
    public void tick() {
        super.tick();

        updateLoaderRotation();
        updateAngleRotation();

        float angle = getAngle();
        CatapultState state = getState();

        if(state == CatapultState.LOADING && angle == -0.65F){
            setState(CatapultState.LOADED);
        }

        if(state == CatapultState.SHOOTING && angle == 0.65F){
            setState(CatapultState.SHOT);
            shootWeapon();
        }
    }

    public void updateLoaderRotation() {
        loaderRotation += getLoaderRotationAmount();
    }

    public float getLoaderRotation(float partialTicks) {
        return loaderRotation + getLoaderRotationAmount() * partialTicks;
    }

    public float getLoaderRotationAmount() {
        return this.getState() == CatapultState.LOADING ? 0.13F : 0F;
    }

    public void updateAngleRotation() {
        setAngle((float) Mth.clamp(getAngle() + getAngleRotationAmount(), -0.65, 0.65));
    }

    public float getAngleRotation(float partialTicks) {
        return (float) Mth.clamp(getAngle() + getAngleRotationAmount() * partialTicks, -0.65, 0.65);
    }
    public float getAngleRotationAmount() {
        if(getState() == CatapultState.LOADING) return -0.004F;
        else if (getState() == CatapultState.SHOOTING) return 0.3F + 0.01F * getRange();
        else return 0;
    }

    public void setRange(float x) {
        if(x > 100) x = 100;
        if(x < 0) x = 0;

        entityData.set(RANGE, x);
    }

    public float getRange(){
        return entityData.get(RANGE);
    }

    public void setAngle(float x) {
        entityData.set(ANGLE, x);
    }

    public float getAngle(){
        return entityData.get(ANGLE);
    }

    public void setState(CatapultState catapultState) {
        if(getControllingPassenger() != null) {
            this.driver = getControllingPassenger();
        }
        this.entityData.set(STATE, catapultState.getIndex());
    }

    public CatapultState getState(){
        return CatapultState.fromIndex(entityData.get(STATE));
    }

    @Override
    public boolean itemInteraction(Player player, InteractionHand interactionHand) {
        //TODO: add load mechanic
        if(getState() == CatapultState.SHOT){
            setState(CatapultState.LOADING);
        }

        if(getState() == CatapultState.LOADED){
            setState(CatapultState.PROJECTILE_LOADED);
            return true;
        }

        if(getState() == CatapultState.PROJECTILE_LOADED){
            setState(CatapultState.SHOOTING);
            return true;
        }

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
        return 10;//TODO: CONFIG
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
        float speed = 3.0F + range * 0.125F;
        double accuracy = 0F;// 0 = 100%
        double yShootVec = forward.y() + 35F/40F;

        this.shoot(forward, yShootVec, this.getControllingPassenger(), speed, accuracy);


    }

    /*
     * Method Important for reflection
     */
    public void shoot(Vec3 shootVec, double yShootVec, LivingEntity driverEntity, float speed, double accuracy){
        //TODO: getter for projectile type
        if(driverEntity == null){
            if(driver == null) return;
            driverEntity = driver;

        }
        CatapultCobbleProjectile projectile = new CatapultCobbleProjectile(this.getCommandSenderWorld(), driverEntity, this.getX(), this.getY() + 3.75, this.getZ());
        projectile.shoot(shootVec.x(), yShootVec, shootVec.z(), speed, (float) accuracy);
        this.getCommandSenderWorld().addFreshEntity(projectile);

        this.playShootSound();
        //this.consumeProjectile();

    }

    @Override
    public void playShootSound() {
        //TODO: add real catapult sound
        this.playSound(SoundEvents.TRIDENT_THROW, 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public void tigger() {
        setState(CatapultState.LOADING);
    }


    public enum CatapultState {
        LOADING(0),
        LOADED(1),
        PROJECTILE_LOADED(2),
        SHOOTING(3),
        SHOT(4);

        private final int index;
        CatapultState(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static CatapultState fromIndex(int x){
            for (CatapultState state : CatapultState.values()) {
                if (state.getIndex() == x) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Invalid Status index: " + x);
        }
    }


    public enum CatapultProjectiles {
        EMPTY(0),
        COBBLE_SHOT(1),
        FIRE_SHOT(2),
        EXPLOSION_SHOT(3),
        SPLASH_SHOT(4);

        private final int index;
        CatapultProjectiles(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static CatapultProjectiles fromIndex(int x){
            for (CatapultProjectiles state : CatapultProjectiles.values()) {
                if (state.getIndex() == x) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Invalid Status index: " + x);
        }
    }
}

