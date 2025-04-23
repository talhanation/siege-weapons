package com.talhanation.siegeweapons.entities;

import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.ModTexts;
import com.talhanation.siegeweapons.entities.projectile.*;
import com.talhanation.siegeweapons.init.ModItems;
import com.talhanation.siegeweapons.init.ModSounds;
import com.talhanation.siegeweapons.inventory.VehicleInventoryMenu;
import com.talhanation.siegeweapons.math.Kalkuel;
import com.talhanation.siegeweapons.network.MessageLoadAndShootWeapon;
import com.talhanation.siegeweapons.network.MessageOpenGUI;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BallistaEntity extends AbstractInventoryVehicleEntity implements IShootingWeapon{

    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(BallistaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> TRIGGERING = SynchedEntityData.defineId(BallistaEntity.class, EntityDataSerializers.BOOLEAN);
    private float loaderRotation;
    private LivingEntity driver;
    private int loadingTime;
    private boolean showTrajectory;
    private int shootCoolDown;
    public BallistaEntity(EntityType<? extends AbstractInventoryVehicleEntity> entityType, Level world) {
        super(entityType, world);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(TRIGGERING, false);
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
        if(--shootCoolDown > 0) return;
        if(isTriggering() && getState() == BallistaState.UNLOADED){
            playLoadingSound();
            if(++loadingTime >= 100){
                loadingTime = 0;
                setState(BallistaState.LOADED);
                return;
            }
        }

        if(this.isTriggering() && getState() == BallistaState.PROJECTILE_LOADED){
            setState(BallistaState.UNLOADED);
            shootWeapon();
            return;
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
            this.driver.setYRot(this.getYRot());
            this.driver.setXRot(this.getXRot());
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
        return isTriggering() && getState() == BallistaState.UNLOADED ? 0.13F : 0F;
    }

    @Override
    public void openGUI(Player player) {
        if (player instanceof ServerPlayer) {
            NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return BallistaEntity.this.getName();
                }

                @Override
                public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                    return new VehicleInventoryMenu(i, BallistaEntity.this, playerInventory);
                }
            }, packetBuffer -> {packetBuffer.writeUUID(BallistaEntity.this.getUUID());
            });
        } else {
            Main.SIMPLE_CHANNEL.sendToServer(new MessageOpenGUI(BallistaEntity.this));
        }
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
        return 300;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(ModItems.BALLISTA_ITEM.get());
    }

    public Component getVehicleTypeName(){
        return ModTexts.BALLISTA;
    }

    @Override
    public boolean itemInteraction(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getMainHandItem();
        BallistaState state = getState();
        if(state == BallistaState.LOADED && itemStack.getItem() == ModItems.BALLISTA_PROJECTILE_ITEM.get()){
            playLoadedSound();
            setState(BallistaState.PROJECTILE_LOADED);
            itemStack.shrink(1);
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

    protected void positionRider(Entity rider, Entity.MoveFunction moveFunction) {
        super.positionRider(rider, moveFunction);
        if (this.hasPassenger(rider)) {
            Vec3 vec = getDriverPosition();

            moveFunction.accept(rider, this.getX() + vec.x, this.getY(), this.getZ() + vec.z);
        }
    }
    public float projectileSpeed = 3.5F;
    @Override
    public void shootWeapon() {
        Vec3 forward = this.getForward();
        double yShootVec = forward.y();
        this.shoot(forward, yShootVec, this.getControllingPassenger(), projectileSpeed);
        this.shootCoolDown = 30;
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
    }

    @Override
    public void playShootSound() {
        this.playSound(ModSounds.BALLISTA_SHOT.get(), 3.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
    }

    private int lastPlayedPhase = -1;
    public void playLoadedSound() {
        this.playSound(ModSounds.BALLISTA_LOADED.get(), 3.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
    }
    public void playLoadingSound() {
        int phase = loadingTime / 20;

        if (phase != lastPlayedPhase) {
            SoundEvent loadingSound = switch (phase) {
                case 0 -> ModSounds.BALLISTA_DRAW_0.get();
                case 1 -> ModSounds.BALLISTA_DRAW_1.get();
                case 2 -> ModSounds.BALLISTA_DRAW_2.get();
                case 3 -> ModSounds.BALLISTA_DRAW_3.get();
                case 4 -> ModSounds.BALLISTA_DRAW_4.get();
                case 5 -> ModSounds.BALLISTA_DRAW_5.get();
                default -> null;
            };

            if (loadingSound != null) {
                this.getCommandSenderWorld().playSound(null, this.getX(), this.getY() + 4, this.getZ(),
                        loadingSound, this.getSoundSource(), 5.3F, 0.8F + 0.4F * this.random.nextFloat());
                lastPlayedPhase = phase;
            }
        }
    }

    @Override
    public void updateTrigger(boolean trigger, LivingEntity livingEntity) {
        boolean needsUpdate = false;

        if (this.isTriggering() != trigger) {
            this.setTriggering(trigger);
            needsUpdate = true;
        }

        if (this.getCommandSenderWorld().isClientSide && needsUpdate && livingEntity instanceof Player) {
            Main.SIMPLE_CHANNEL.sendToServer(new MessageLoadAndShootWeapon(trigger, livingEntity.getUUID()));
        }
    }

    @Override
    public void setShowTrajectory(boolean rightClickKey) {
        this.showTrajectory = rightClickKey;
    }

    @Override
    public boolean getShowTrajectory() {
        return showTrajectory;
    }

    public boolean isTriggering() {
        return this.entityData.get(TRIGGERING);
    }

    public void setTriggering(boolean trigger) {
        this.entityData.set(TRIGGERING, trigger);
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
