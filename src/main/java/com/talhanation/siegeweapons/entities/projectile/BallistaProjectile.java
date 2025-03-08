package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
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
        setBaseDamage(25F);
    }
    public boolean wasShot = false;
    public int counter = 0;
    private float rotation;

    public float getAccuracy(){
        return 0F;
    };
    protected BallistaProjectile(EntityType<? extends BallistaProjectile> type, Level world) {
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
    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide()) {

            //TODO: add fire option
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return Items.ARROW.getDefaultInstance();
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if(this.level().isClientSide()) this.hitParticles();
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        if (!this.level().isClientSide()) {
            Entity hitEntity = hitResult.getEntity();
            Entity ownerEntity = this.getOwner();

            if (ownerEntity instanceof LivingEntity) {
                if (ownerEntity.getTeam() != null && ownerEntity.getTeam().isAlliedTo(hitEntity.getTeam()) && !ownerEntity.getTeam().isAllowFriendlyFire())
                    return;
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
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        return false;
    }
}
