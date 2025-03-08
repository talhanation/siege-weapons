package com.talhanation.siegeweapons.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.models.projectile.BallistaProjectileModel;
import com.talhanation.siegeweapons.client.models.projectile.CatapultProjectileModel;
import com.talhanation.siegeweapons.entities.projectile.BallistaProjectile;
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BallistaProjectileRenderer extends EntityRenderer<BallistaProjectile> {

    private static final ResourceLocation[] TEXTURE = {
            new ResourceLocation(Main.MOD_ID,"textures/entity/ballista_projectile.png")
    };
    public BallistaProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    double translation = -0.95D;
    @Override
    public ResourceLocation getTextureLocation(BallistaProjectile entity) {
        return TEXTURE[0];
    }
    private final BallistaProjectileModel<BallistaProjectile> model = new BallistaProjectileModel<>();

    @Override
    public void render(BallistaProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 180.0F));
        poseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));

        poseStack.translate(0.0D, translation,0.0D);

        this.model.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);

        VertexConsumer ivertexbuilder = multiBufferSource.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();

    }
}
