package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import com.talhanation.siegeweapons.init.ModEntityTypes;
import com.talhanation.siegeweapons.init.ModItems;
import com.talhanation.siegeweapons.init.ModSounds;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BallistaProjectile extends AbstractArrow {
    //Arrow
    public static BallistaProjectile factory(EntityType<? extends BallistaProjectile> entityType, Level level) {
        return new BallistaProjectile(entityType, level);
    }

    public BallistaProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.BALLISTA_PROJECTILE.get(), d1, d2, d3, world);
        setOwner(owner);
        setCritArrow(true);
        setKnockback(1);
        setPierceLevel((byte) 1);
        setBaseDamage(12.5F);
    }
    public boolean wasShot = false;
    public int counter = 0;
    private float rotation;

    public float getAccuracy(){
        return 0F;
    };
    public BallistaProjectile(EntityType<? extends BallistaProjectile> type, Level world) {
        super(type, world);
    }

    public float getProjectileRotation(float partialTicks){
        return rotation + getProjectileRotationAmount() * partialTicks;
    }

    public float getProjectileRotationAmount(){
        return inGround ? 0F : 0.055F;
    }

    public void updateProjectileRotation() {
        rotation += getProjectileRotationAmount();
    }

    @Override
    public void tick() {
        super.tick();

        if (wasShot){
            counter++;
        }

        if (counter > 3200){
            this.discard();
        }
        updateProjectileRotation();

    }

    public Direction getDirectionOfImpact() {
        Vec3 movement = this.getDeltaMovement();
        if (movement == Vec3.ZERO) {
            return Direction.UP;
        }

        return Direction.getNearest(movement.x, movement.y, movement.z);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        //TODO: ADD FIRE OPTION
        if (!level().isClientSide && this.inGround && lastState != null) {
            Direction impactDirection = this.getDirectionOfImpact();
            Vec3 normal = Vec3.atLowerCornerOf(impactDirection.getNormal()).normalize();

            Vec3 impactPos = this.position().add(normal.scale(0.1));

            for (int i = 0; i < 100; i++) {
                double baseSpeed = 0.35;

                double dx = normal.x * baseSpeed;
                double dy = normal.y * baseSpeed;
                double dz = normal.z * baseSpeed;

                ((ServerLevel) level()).sendParticles(
                        new BlockParticleOption(ParticleTypes.BLOCK, this.lastState),
                        impactPos.x, impactPos.y, impactPos.z,
                        1, dx, dy, dz, 0.05
                );
            }
        }
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return ModItems.BALLISTA_PROJECTILE_ITEM.get().getDefaultInstance();
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if(this.level().isClientSide()) this.hitParticles();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult hitResult) {
        if (!this.level().isClientSide()) {
            Entity hitEntity = hitResult.getEntity();
            Entity ownerEntity = this.getOwner();

            if (ownerEntity instanceof LivingEntity) {
                if (ownerEntity.getTeam() != null && ownerEntity.getTeam().isAlliedTo(hitEntity.getTeam()) && !ownerEntity.getTeam().isAllowFriendlyFire())
                    return;


                if(hitEntity instanceof AbstractVehicleEntity){
                    this.level().playSound(null, this.getX(), this.getY() + 4 , this.getZ(), ModSounds.SIEGEWEAPON_HIT.get(), this.getSoundSource(), 5.3F, 0.8F + 0.4F * this.random.nextFloat());
                }
            }
        }
        super.onHitEntity(hitResult);
    }

    public void hitParticles(){

    }
    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(@NotNull DamageSource p_70097_1_, float p_70097_2_) {
        return false;
    }
}
