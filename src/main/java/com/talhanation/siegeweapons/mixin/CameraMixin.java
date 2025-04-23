package com.talhanation.siegeweapons.mixin;

import com.talhanation.siegeweapons.entities.BallistaEntity;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow
    private Vec3 position;
    @Shadow private float xRot;
    @Shadow private float yRot;

    @Inject(method = "setup", at = @At("HEAD"), cancellable = true)
    public void onAimingWithBallista(BlockGetter blockGetter, Entity renderViewEntity, boolean isThirdPerson, boolean isMirror, float partialTicks, CallbackInfo ci) {
        if (renderViewEntity instanceof Player player) {
            if (player.getVehicle() instanceof BallistaEntity ballista && ballista.getShowTrajectory()) {
                // Position etwas hinter und über der Ballista setzen
                double yawRad = Math.toRadians(ballista.getYRot());

                Vec3 rotatedOffset = new Vec3(-Math.sin(yawRad) * 0.15, 1.6, Math.cos(yawRad) * 0.15);

                this.position = ballista.position().add(rotatedOffset);
                this.xRot = ballista.getXRot();
                this.yRot = ballista.getYRot();

                ci.cancel(); // Verhindert, dass die Original-Methode überschreibt
            }
        }
    }
}
