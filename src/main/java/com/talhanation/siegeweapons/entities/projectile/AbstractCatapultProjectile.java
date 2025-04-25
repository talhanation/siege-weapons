package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.entities.AbstractVehicleEntity;
import com.talhanation.siegeweapons.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
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

    public abstract boolean getFireSpread();
    public abstract boolean getExplode();
    public float accuracy;
    public float hurtDamage;
    public float areaDamage;
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

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public void setAreaDamage(float areaDamage) {
        this.areaDamage = areaDamage;
    }

    public void setHurtDamage(float hurtDamage) {
        this.hurtDamage = hurtDamage;
    }

    public float getAreaDamage() {
        return areaDamage;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public float getHurtDamage() {
        return hurtDamage;
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide()) {

            if(!isInWater()){
                BlockPos pos = blockHitResult.getBlockPos();
                if(getFireSpread()){
                    igniteArea(this.level(), pos, 2, 2);
                }
                if(getExplode()) {
                    this.level().explode(this.getOwner(), this.getX(), this.getY(), this.getZ(), getAreaDamage(), getFireSpread(), Level.ExplosionInteraction.MOB);
                }
            }

            this.remove(RemovalReason.KILLED);
        }

        this.playSound(this.getBlockHitSound(), 3.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
    }

    public SoundEvent getBlockHitSound() {
        return SoundEvents.GENERIC_EXPLODE;
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


            hitEntity.hurt(this.damageSources().thrown(this, ownerEntity), (float) getHurtDamage());//TODO: CONFIG
            this.setAreaDamage(areaDamage/2);
            this.setHurtDamage(hurtDamage/2);
        }
    }

    public void hitParticles(){
        for (int i = 0; i < 10; ++i) {
            double d0 = this.random.nextGaussian() * 0.03D;
            double d1 = this.random.nextGaussian() * 0.03D;
            double d2 = this.random.nextGaussian() * 0.03D;
            double d3 = 20.0D;
            this.level().addParticle(ParticleTypes.POOF, this.getX(1.0D) - d0 * d3, this.getRandomY() - d1 * d3, this.getRandomZ(2.0D) - d2 * d3, d0, d1, d2);
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
            this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ() , 0, 0, 0);
        }

        for (int i = 0; i < 50; ++i) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
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

    public void igniteArea(Level level, BlockPos center, int radiusX, int radiusZ) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        for (int dx = -radiusX; dx <= radiusX; dx++) {
            for (int dz = -radiusZ; dz <= radiusZ; dz++) {
                for (int dy = -3; dy <= 3; dy++) {
                    BlockPos pos = center.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    BlockPos below = pos.below();
                    BlockState belowState = level.getBlockState(below);

                    if (CampfireBlock.canLight(state) || CandleBlock.canLight(state) || CandleCakeBlock.canLight(state)) {
                        BlockState litState = state.setValue(BlockStateProperties.LIT, true);
                        level.setBlock(pos, litState, 11);
                        level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                        level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
                        continue;
                    }

                    for (Direction direction : Direction.values()) {
                        BlockPos adjacent = pos.relative(direction);
                        if (BaseFireBlock.canBePlacedAt(level, adjacent, direction)) {
                            BlockState fireState = BaseFireBlock.getState(level, adjacent);
                            level.setBlock(adjacent, fireState, 11);
                            level.playSound(null, adjacent, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 2.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                            level.gameEvent(null, GameEvent.BLOCK_PLACE, adjacent);
                            break;
                        }
                    }

                    boolean isReplaceable = state.isAir() || state.is(BlockTags.SNOW);
                    boolean canPlaceFire = Blocks.FIRE.canSurvive(belowState, level, pos);

                    if (isReplaceable && belowState.isSolidRender(level, below) && canPlaceFire) {
                        serverLevel.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                        level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                        level.gameEvent(null, GameEvent.BLOCK_PLACE, pos);
                    }
                }
            }
        }
    }

    @Override
    protected @NotNull ParticleOptions getTrailParticle() {
        return ParticleTypes.SMOKE;
    }
}
