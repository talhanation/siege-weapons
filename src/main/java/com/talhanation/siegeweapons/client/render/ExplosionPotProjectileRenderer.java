package com.talhanation.siegeweapons.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.models.projectile.CatapultExplosionPotProjectileModel;
import com.talhanation.siegeweapons.entities.projectile.ExplosionPotProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ExplosionPotProjectileRenderer extends EntityRenderer<ExplosionPotProjectile> {

    private static final ResourceLocation[] TEXTURE = {
            new ResourceLocation(Main.MOD_ID,"textures/entity/explosion_pot.png")
    };
    public ExplosionPotProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 1.5F;
    }

    @Override
    public ResourceLocation getTextureLocation(ExplosionPotProjectile entity) {
        return TEXTURE[0];
    }
    private final CatapultExplosionPotProjectileModel model = new CatapultExplosionPotProjectileModel();
    @Override
    public void render(ExplosionPotProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, -0.0D, 0.0D);

        poseStack.scale(-1.0F, -1.0F, 1.0F);

        poseStack.translate(0.0D, 0.0D,0.0D);

        this.model.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);

        VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();

    }
}
