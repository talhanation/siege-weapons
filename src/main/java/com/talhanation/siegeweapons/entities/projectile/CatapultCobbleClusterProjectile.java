package com.talhanation.siegeweapons.entities.projectile;

import com.talhanation.siegeweapons.init.ModEntityTypes;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
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

    public float getDamage(){
        return 20F;
    }

    @Override
    public float getAreaDamage() {
        return 0.5F;
    }
    @Override
    public boolean getFireSpread() {
        return false;
    }

    @Override
    public boolean getExplode() {
        return true;
    }

    @Override
    public float getAccuracy() {
        return 7;
    }

    public CatapultCobbleClusterProjectile(EntityType<? extends CatapultCobbleClusterProjectile> type, Level world) {
        super(type, world);
    }
    public CatapultCobbleClusterProjectile(Level world, LivingEntity owner, double d1, double d2, double d3) {
        super(ModEntityTypes.COBBLE_CLUSTER_PROJECTILE.get(), owner, d1, d2, d3, world);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        BlockState state = level().getBlockState(blockHitResult.getBlockPos());
        for(int i = 0; i < 30; ++i) {
            Vec3 vec3 = Vec3.atCenterOf(blockHitResult.getBlockPos()).add(0.0D, (double) -0.65F, 0.0D);
            this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), vec3.x, vec3.y, vec3.z, 0.0D, 0.0D, 0.0D);
        }
        super.onHitBlock(blockHitResult);
    }

}
