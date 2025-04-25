package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class CatapultCobbleClusterProjectile extends AbstractCatapultProjectile {

    public static CatapultCobbleClusterProjectile factory(EntityType<? extends CatapultCobbleClusterProjectile> entityType, Level level) {
        return new CatapultCobbleClusterProjectile(entityType, level);
    }

    @Override
    public boolean getFireSpread() {
        return false;
    }

    @Override
    public boolean getExplode() {
        return false;
    }
    public CatapultCobbleClusterProjectile(EntityType<? extends CatapultCobbleClusterProjectile> type, Level world) {
        super(type, world);
        setHurtDamage(25F);
        setAccuracy(7F);
    }
    public CatapultCobbleClusterProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.COBBLE_CLUSTER_PROJECTILE.get(), owner, d1, d2, d3, world);
        setHurtDamage(25F);
        setAccuracy(7F);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        if(!level().isClientSide()){
            BlockState state = level().getBlockState(blockHitResult.getBlockPos());
            for (int i = 0; i < 200; ++i) {
                double d0 = this.random.nextGaussian() * 0.03D;
                double d1 = this.random.nextGaussian() * 0.03D;
                double d2 = this.random.nextGaussian() * 0.03D;
                double d3 = 20.0D;
                double x = this.getX(1.0D) - d0 * d3;
                double y = this.getY() - d1 * d3  + i * 0.012;
                double z = this.getRandomZ(2.0D) - d2 * d3;
                ((ServerLevel) level()).sendParticles(
                        new BlockParticleOption(ParticleTypes.BLOCK, state),
                        x, y, z,
                        1, 0, 0, 0, 0.05
                );
            }
        }
        super.onHitBlock(blockHitResult);
    }

    @Override
    public SoundEvent getBlockHitSound() {
        return SoundEvents.STONE_HIT;
    }


}
