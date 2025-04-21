package com.talhanation.siegeweapons.entities;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.math.Kalkuel;
import com.talhanation.siegeweapons.network.MessageUpdateVehicleControl;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public abstract class AbstractVehicleEntity extends Entity {

    private static final EntityDataAccessor<Boolean> FORWARD = SynchedEntityData.defineId(AbstractVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BACKWARD = SynchedEntityData.defineId(AbstractVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LEFT = SynchedEntityData.defineId(AbstractVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RIGHT = SynchedEntityData.defineId(AbstractVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(AbstractVehicleEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> HEALTH = SynchedEntityData.defineId(AbstractVehicleEntity.class, EntityDataSerializers.FLOAT);

    private final float maxSpeed = getMaxSpeedInKmH() / (60F * 1.15F);
    private float wheelRotation;
    private int steps;
    private double clientX;
    private double clientY;
    private double clientZ;
    private double clientYaw;
    private double clientPitch;

    protected float deltaRotation;
    private boolean drivenPrevTick;

    public abstract int getMaxPassengerSize();
    public abstract int getMaxSpeedInKmH();
    public abstract float getMaxHealth();

    public AbstractVehicleEntity(EntityType<?> type, Level level) {
        super(type, level);
    }
    @Override
    protected void defineSynchedData() {
        this.entityData.define(FORWARD, false);
        this.entityData.define(BACKWARD, false);
        this.entityData.define(LEFT, false);
        this.entityData.define(RIGHT, false);
        this.entityData.define(SPEED, 0F);
        this.entityData.define(HEALTH, getMaxHealth());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setHealth(compoundTag.getFloat("health"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putFloat("health", this.getHealth());
    }

    @Override
    public void tick() {

        if (!getCommandSenderWorld().isClientSide()) {
            this.xo = getX();
            this.yo = getY();
            this.zo = getZ();
        }
        super.tick();
        tickLerp();
        this.updateSmokeWhenLowHealth();
        this.updateGravity();
        float xRot = this.getXRot();
        float yRot = this.getYRot();
        final LivingEntity driver = this.getControllingPassenger();
        this.control(driver, xRot, yRot);
        move(MoverType.SELF, getDeltaMovement());
        updateWheelRotation();
    }

    private void updateSmokeWhenLowHealth() {
        if(this.getCommandSenderWorld().isClientSide()){
            if (getHealth() < getMaxHealth() * 0.5F) {
                this.getCommandSenderWorld().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getY() + 1.0D, this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public float maxUpStep() {
        return 1.0F;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        if(this.getFirstPassenger() instanceof LivingEntity driver){
            return driver;
        }

        return null;
    }

    public void control(Entity driver, float xRot, float yRot) {
        float speed = Kalkuel.subtractToZero(getSpeed(), getRollResistance());
        if(driver != null) {

            if (isForward()) {
                if (speed <= maxSpeed) {
                    speed = Math.min(speed + 0.01F, maxSpeed);
                }
            }

            if (isBackward()) {
                if (speed >= -maxSpeed) {
                    speed = Math.max(speed - 0.01F, -maxSpeed);
                }
            }

            deltaRotation = 0;
            if(isLeft()){
                --deltaRotation;
            }

            if(isRight()){
                ++deltaRotation;
            }

            float newYRot = yRot + this.deltaRotation * 0.25F;

            this.setXRot(xRot);
            this.setYRot(newYRot);
        }
        else {
            setForward(false);
            setBackward(false);
            setLeft(false);
            setRight(false);
        }

        this.setSpeed(speed);
        setDeltaMovement(Kalkuel.calculateMotionX(this.getSpeed(), this.getYRot()), getDeltaMovement().y, Kalkuel.calculateMotionZ(this.getSpeed(), this.getYRot()));
    }

    /************************************
     * Used by Recruits Mod -> Player == null
     ************************************/
    public void updateControls(boolean forward, boolean backward, boolean left, boolean right, @Nullable LivingEntity livingEntity) {
        boolean needsUpdate = false;

        if (this.isForward() != forward) {
            this.setForward(forward);
            needsUpdate = true;
        }

        if (this.isBackward() != backward) {
            this.setBackward(backward);
            needsUpdate = true;
        }

        if (this.isLeft() != left) {
            this.setLeft(left);
            needsUpdate = true;
        }

        if (this.isRight() != right) {
            this.setRight(right);
            needsUpdate = true;
        }
        if (this.getCommandSenderWorld().isClientSide && needsUpdate && livingEntity instanceof Player) {
            Main.SIMPLE_CHANNEL.sendToServer(new MessageUpdateVehicleControl(forward, backward, left, right, livingEntity.getUUID()));
        }
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return this.getPassengers().size() < getMaxPassengerSize();
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.35F;
    }

    private float getRollResistance() {
        return 0.005F;
    }

    private void tickLerp() {
        if (this.isControlledByLocalInstance()) {
            this.steps = 0;
            this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
        }

        if (this.steps > 0) {
            double d0 = getX() + (clientX - getX()) / (double) steps;
            double d1 = getY() + (clientY - getY()) / (double) steps;
            double d2 = getZ() + (clientZ - getZ()) / (double) steps;
            double d3 = Mth.wrapDegrees(clientYaw - (double) getYRot());
            setYRot((float) ((double) getYRot() + d3 / (double) steps));
            setXRot((float) ((double) getXRot() + (clientPitch - (double) getXRot()) / (double) steps));
            --steps;
            setPos(d0, d1, d2);
            setRot(getYRot(), getXRot());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.clientX = x;
        this.clientY = y;
        this.clientZ = z;
        this.clientYaw = yaw;
        this.clientPitch = pitch;
        this.steps = 10;
    }

    private void updateGravity() {
        if (isNoGravity()) {
            setDeltaMovement(getDeltaMovement().x, 0D, getDeltaMovement().z);
            return;
        }
        setDeltaMovement(getDeltaMovement().x, getDeltaMovement().y - 0.15D, getDeltaMovement().z);
    }

    public void setForward(boolean forward) {
        entityData.set(FORWARD, forward);
    }

    public void setBackward(boolean backward ) {
        entityData.set(BACKWARD, backward);
    }

    public void setLeft(boolean left) {
        entityData.set(LEFT, left);
    }

    public void setRight(boolean right) {
        entityData.set(RIGHT, right);
    }
    public void setSpeed(float speed) {
        entityData.set(SPEED, speed);
    }

    public float getSpeed(){
        return entityData.get(SPEED);
    }

    public void setHealth(float health) {
        entityData.set(HEALTH, health);
    }

    public float getHealth(){
        return entityData.get(HEALTH);
    }
    public boolean canCollideWith(Entity entity) {
        return canVehicleCollide(this, entity);
    }

    public static boolean canVehicleCollide(Entity entity, Entity entity2) {
        return (entity2.canBeCollidedWith() || entity2.isPushable()) && !entity.isPassengerOfSameVehicle(entity2);
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean isPushable() {
        return false;
    }

    public float getWheelRotationAmount() {
        return 120F * getSpeed() * 0.0125F;
    }

    public void updateWheelRotation() {
        wheelRotation += getWheelRotationAmount();
    }

    public float getWheelRotation(float partialTicks) {
        return wheelRotation + getWheelRotationAmount() * partialTicks;
    }
    public boolean isForward() {
        if (this.getControllingPassenger() == null) {
            return false;
        }
        return entityData.get(FORWARD);
    }

    public boolean isBackward() {
        if (this.getControllingPassenger() == null) {
            return false;
        }
        return entityData.get(BACKWARD);
    }

    public boolean isLeft() {
        return entityData.get(LEFT);
    }

    public boolean isRight() {
        return entityData.get(RIGHT);
    }

    public boolean canBeHitByProjectile() {
        return true;
    }

    public boolean isPickable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand interactionHand) {
        if (this.itemInteraction(player, interactionHand)) {
            return InteractionResult.CONSUME;
        } else if (player.isSecondaryUseActive() && this instanceof AbstractInventoryVehicleEntity inventoryVehicle) {
            inventoryVehicle.openGUI(player);
            return InteractionResult.CONSUME;
        } else if (this.getPassengers().size() == getMaxPassengerSize()) {
            return InteractionResult.PASS;
        } else if (!this.getCommandSenderWorld().isClientSide()) {
            return this.tryRiding(player) ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    protected boolean tryRiding(Entity entity) {
        if (this.getCommandSenderWorld().isClientSide()) return false;

        if(entity.startRiding(this)){
            entity.setYRot(this.getYRot());
            entity.setXRot(this.getXRot());
            return true;
        }
        return false;
    }

    public abstract boolean itemInteraction(Player player, InteractionHand interactionHand);

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        }
        else if (!this.getCommandSenderWorld().isClientSide() && !this.isRemoved()) {
            float health = this.getHealth();
            float newHealth = health - f;
              this.setHealth(newHealth);

            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getEntity());

            boolean bl = damageSource.getEntity() instanceof Player player && player.getAbilities().instabuild && player.isCrouching();


            if (this.getHealth() <= 0) {
                kill();
            }
            if(bl){
                this.discard();
            }

            return true;
        } else {
            return true;
        }
    }
    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        Vec3 vec = getDriverPosition();
        return new Vec3(this.getX() + vec.x, this.getY(), this.getZ() + vec.z);
    }

    public abstract Vec3 getDriverPosition();
}
