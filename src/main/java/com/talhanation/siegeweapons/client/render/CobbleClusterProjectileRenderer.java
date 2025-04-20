package com.talhanation.siegeweapons.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.talhanation.siegeweapons.Main;
import com.talhanation.siegeweapons.client.models.item.CobbleClusterItemModel;
import com.talhanation.siegeweapons.client.models.projectile.CatapultCobbleClusterProjectileModel;
import com.talhanation.siegeweapons.entities.projectile.CatapultCobbleClusterProjectile;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CobbleClusterProjectileRenderer extends EntityRenderer<CatapultCobbleClusterProjectile> {
    private final boolean isItem;
    private final EntityModel<CatapultCobbleClusterProjectile> model;
    private static final ResourceLocation[] TEXTURE = {
            new ResourceLocation(Main.MOD_ID,"textures/entity/cobble_projectile.png")
    };
    public CobbleClusterProjectileRenderer(EntityRendererProvider.Context context, boolean isItem) {
        super(context);
        this.shadowRadius = 0.5F;
        this.isItem = isItem;
        this.model =  isItem ? new CobbleClusterItemModel<>() : new CatapultCobbleClusterProjectileModel<>();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CatapultCobbleClusterProjectile entity) {
        return TEXTURE[0];
    }

    @Override
    public void render(@NotNull CatapultCobbleClusterProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight) {
        if(model != null){
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
}
