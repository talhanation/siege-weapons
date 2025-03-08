package com.talhanation.siegeweapons.entities.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCatapultProjectile extends AbstractHurtingProjectile {

    public boolean inWater = false;
    public boolean wasShot = false;
    public int counter = 0;
    private float rotation;
    public abstract float getDamage();
    public abstract float getAreaDamage();
    public abstract float getAreaDamageChance();
    public abstract boolean getFireSpread();
    public abstract boolean getExplode();
    public abstract float getAccuracy();
    protected AbstractCatapultProjectile(EntityType<? extends AbstractCatapultProjectile> type, Level world) {
        super(type, world);
    }

    public AbstractCatapultProjectile(EntityType<? extends AbstractCatapultProjectile> type, LivingEntity owner, double d1, double d2, double d3, Level world) {
        super(type, owner, d1, d2, d3, world);
        this.moveTo(d1, d2, d3, this.getYRot(), this.getXRot());
    }

    public float getProjectileRotation(float partialTicks){
        return rotation + getProjectileRotationAmount() * partialTicks;
    }

    public float getProjectileRotationAmount(){
        return 0.025F;
    }

    public void updateProjectileRotation() {
        rotation += getProjectileRotationAmount();
    }

    @Override
    public void tick() {
        this.baseTick();

        Vec3 vector3d = this.getDeltaMovement();
        HitResult raytraceresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

        if (raytraceresult.getType() != HitResult.Type.MISS) {
            this.onHit(raytraceresult);
        }

        double d0 = this.getX() + vector3d.x;
        double d1 = this.getY() + vector3d.y;
        double d2 = this.getZ() + vector3d.z;
        this.updateRotation();
        float f = 0.99F;
        float f1 = 0.06F;
        float f2 = -0.05F;
        this.setDeltaMovement(vector3d.scale(f));
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -f1, 0.0D));
        }
        this.setPos(d0, d1, d2);

        if(isAlive()){
            this.setWasShot(true);
        }

        if(isInWater()){
            if (this.level().isClientSide() && !isUnderWater()) waterParticles();

            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -f2, 0.0D));
            this.setInWater(true);
        }

        if (wasShot){
            counter++;
        }

        if (counter < 4){
            if (this.level().isClientSide()) tailParticles();
        }

        if (isInWater() && counter > 400){
            this.discard();
        }


        updateProjectileRotation();
    }

    public void setWasShot(boolean bool){
        if (bool != wasShot){
            wasShot = true;
            if (this.level().isClientSide()) {
                this.shootParticles();
            }
        }
    }

    public void setInWater(boolean bool){
        if (bool != inWater){
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 3.3F, 0.8F + 0.4F * this.random.nextFloat());
            inWater = true;
            //this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide()) {

            if(!isInWater()){
                if(getExplode()) this.level().explode(this.getOwner(), getX(), getY(), getZ(), getAreaDamage(), getFireSpread(), Level.ExplosionInteraction.MOB);
                else applyDirectionalDamage(this.level(), blockHitResult.getBlockPos(), getAreaDamageChance());
            }

            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if(this.level().isClientSide()) this.hitParticles();
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        if (!this.level().isClientSide()) {
            Entity hitEntity = hitResult.getEntity();
            Entity ownerEntity = this.getOwner();

            if (ownerEntity instanceof LivingEntity livingOwnerEntity) {
                if(ownerEntity.getTeam() != null && ownerEntity.getTeam().isAlliedTo(hitEntity.getTeam()) && !ownerEntity.getTeam().isAllowFriendlyFire()) return;
                this.doEnchantDamageEffects(livingOwnerEntity, hitEntity);
                this.level().playSound(null, this.getX(), this.getY() + 4 , this.getZ(), SoundEvents.GENERIC_EXPLODE, this.getSoundSource(), 3.3F, 0.8F + 0.4F * this.random.nextFloat());
            }

            hitEntity.hurt(this.damageSources().thrown(this, ownerEntity), (float) getDamage());//TODO: CONFIG
        }
    }

    public void hitParticles(){
        for (int i = 0; i < 300; ++i) {
            double d0 = this.random.nextGaussian() * 0.03D;
            double d1 = this.random.nextGaussian() * 0.03D;
            double d2 = this.random.nextGaussian() * 0.03D;
            double d3 = 20.0D;
            this.level().addParticle(ParticleTypes.ASH, this.getX(1.0D) - d0 * d3, this.getRandomY() - d1 * d3, this.getRandomZ(2.0D) - d2 * d3, d0, d1, d2);
            this.level().addParticle(ParticleTypes.EXPLOSION, this.getX(1.0D) - d0 * d3, this.getRandomY() - d1 * d3, this.getRandomZ(2.0D) - d2 * d3, d0, d1, d2);
        }
    }

    public void waterParticles(){
        for (int i = 0; i < 200; ++i) {
            double d0 = this.random.nextGaussian() * 0.03D;
            double d1 = this.random.nextGaussian() * 0.03D;
            double d2 = this.random.nextGaussian() * 0.03D;
            double d3 = 20.0D;
            this.level().addParticle(ParticleTypes.POOF, this.getX(1.0D) - d0 * d3, this.getRandomY() - d1 * d3  + i * 0.012, this.getRandomZ(2.0D) - d2 * d3, d0, d1, d2);
        }
    }

    public void shootParticles(){
        for (int i = 0; i < 100; ++i) {
            double d0 = this.random.nextGaussian() * 0.03D;
            double d1 = this.random.nextGaussian() * 0.03D;
            double d2 = this.random.nextGaussian() * 0.03D;
            double d3 = 20.0D;
            this.level().addParticle(ParticleTypes.ASH, this.getX(1.0D) - d0 * d3, this.getRandomY() - d1 * d3, this.getRandomZ(2.0D) - d2 * d3, d0, d1, d2);
        }
    }

    public void tailParticles(){
        for (int i = 0; i < 50; ++i) {
            this.level().addParticle(ParticleTypes.ASH, this.getX(), this.getY(), this.getZ() , 0, 0, 0);
        }

        for (int i = 0; i < 50; ++i) {
            this.level().addParticle(ParticleTypes.ASH, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        Entity entity = this.getOwner();
        int i = entity == null ? 0 : entity.getId();
        return new ClientboundAddEntityPacket(this.getId(), this.getUUID(), this.getX(), this.getY(), this.getZ(), this.getXRot(), this.getYRot(), this.getType(), i, new Vec3(this.xPower, this.yPower, this.zPower), 0.0);

    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected @NotNull ParticleOptions getTrailParticle() {
        return ParticleTypes.SMOKE;
    }

    private void applyDirectionalDamage(Level level, BlockPos impactPos, float chance) {
        BlockPos[] directions = {
                impactPos.above(),
                impactPos.below(),
                impactPos.north(),
                impactPos.south(),
                impactPos.east(),
                impactPos.west()
        };

        destroyBlock(level, impactPos, chance);

        for (BlockPos pos : directions) {
            destroyBlock(level, pos, chance);
        }
    }

    private void destroyBlock(Level level, BlockPos pos, float chance) {
        BlockState state = level.getBlockState(pos);
        float hardness = state.getDestroySpeed(level, pos);

        if (hardness == 0 || state.isAir()) {
            return;
        }


        int adjustedChance = (int) Math.max(5, chance - (int) (hardness * 10));
        if (level.random.nextInt(100) < adjustedChance) {
            level.destroyBlock(pos, false);
        }
    }

}
